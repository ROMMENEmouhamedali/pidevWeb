<?php

namespace App\Controller;
use App\Repository\CalenderroomRepository;
use App\Entity\Reservation;
use App\Entity\User;
use App\Services\QrcodeService;
use App\Entity\Room;
use App\Entity\Progressroom;
use App\Repository\ReservationRepository;
use App\Repository\ProgressroomRepository;
use App\Entity\Calenderroom;
use App\Form\ReservationType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Console\Helper\ProgressBar;
use Symfony\Component\Console\Output\ConsoleOutputInterface;
use Symfony\Component\Console\Output\ConsoleSectionOutput;
use Symfony\Component\Console\Output\OutputInterface;



//use Endroid\QrCode\QrCode;
//header("Content-Type: image/png");
//require "vendor/autoload.php";
//require __DIR__ . '/vendor/autoload.php';


/**
 * @Route("/reservation")
 */
class ReservationController extends AbstractController
{
    /**
     * @Route("/", name="app_reservation_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }



        $reservations = $entityManager
            ->getRepository(Reservation::class)
            ->findAll();

        return $this->render('backoffice/reservation/index.html.twig', [
            'reservations' => $reservations,
        ]);
    }  /**
    * @Route("/statistic", name="app_stat")
    */
   public function statistic(ProgressroomRepository $progressroomRepository){
       if (!($this->getUser())) {
           return $this->redirectToRoute('app_home');
       }

       if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
           return $this->redirectToRoute('app_home');
       }


       $progressroomAll=$progressroomRepository->findAll();
        $labels=[];
        $data=[];



    
          foreach($progressroomAll as $progressroom){
           $labels[]=['niveau1'];
           $data[]=$progressroom-> getProgress();





       }


       return $this->render("backoffice/reservation/chart.html.twig", [
           'categNom' => json_encode($labels),

           'categCount' => json_encode($data),
          

       ]);
   }




   




    /**
     * @Route("/listp", name="app_reservation_pdf", methods={"GET"})
     */
    public function listp(ReservationRepository $reservationRepository): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('backoffice/reservation/listp.html.twig', [
            'reservations' => $reservationRepository->findAll(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render('backoffice/reservation/index.html.twig');

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);


        // Send some text response
        return new Response("The PDF file has been succesfully generated !");
    }
    /**
     * @Route("/progressBar", name="app_reservation_pbar", methods={"GET"})
     */
    public function progressBar(OutputInterface $output): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
      // creates a new progress bar (50 units)
$progressBar = new ProgressBar($output, 50);

// starts and displays the progress bar
$progressBar->start();

$i = 0;
while ($i++ < 50) {
    // ... do some work

    // advances the progress bar 1 unit
    $progressBar->advance();

    // you can also advance the progress bar by more than 1 unit
    // $progressBar->advance(3);
}

// ensures that the progress bar is at 100%
$progressBar->finish();
    } 

       
     /**
      * @param ReservationRepository $repository
      * @return Response 
      * @Route("reservation/ListDQL")
      */
    function OrderByRoomNumberDQL(ReservationRepository $repository)
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
           $reservations=$repository->OrderByRoomNumber();
           return $this->render('backoffice/reservation/index.html.twig', [
            'reservations' => $reservations,
        ]);

    }

    /**
     * @Route("/new", name="app_reservation_new", methods={"GET", "POST"})
     * @param QrcodeService $qrcodeService 
     */
    public function new(Request $request, EntityManagerInterface $entityManager, CalenderroomRepository $CRepository,\Swift_Mailer $mailer,ProgressroomRepository $progressroomRepository, QrcodeService $qrcodeService): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        $qrCode=null;
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);
            //get the arrival and departure date and test on them on calender room if there is a room id where state 0 on both dates
       
        
        $AvailableRoomOnDD=$CRepository->findOneBy(array('dateroom' => $reservation->getDeparturedate() ,
        'state'=>'0'));
        
        
        $AvailableRoomOnAD=$CRepository->findOneBy(array('dateroom' => $reservation->getArrivaldate() ,
        'state'=>'0' ));

        $progress=$progressroomRepository->find(1);
        //$progress=$entityManager->getRepository(Progressroom::class)->find(1);
        
        if ($form->isSubmitted() && $form->isValid() && $AvailableRoomOnDD!=NULL && $AvailableRoomOnAD!=NULL ) {
            
            $new=$form->getData();
            $progress->setProgress( $progress->getProgress() + 0.1 );
            $entityManager->persist($reservation);
            $entityManager->flush();
            //$qrCode = $qrcodeService->qrcode($new['arrivaldate']);

            $dateString = $reservation->getArrivaldate()->format('d-m-Y H:i:s');
            $qrCode = $qrcodeService->qrcode( $dateString);


           



            $mail=[];

            $emailUser=$reservation-> getFkUserReservation()->getEmail();
            $arrivaldate= $reservation-> getArrivaldate();
            $departureDate=$reservation-> getDeparturedate();

            $arrivalD = $arrivaldate->format('Y-m-d');
            $departureD=$departureDate->format('Y-m-d');
    
            $message = (new \Swift_Message("Booking confirmation between dates : ".$arrivalD." and ". $departureD ))
                //->setSubject('Booking confirmation between dates',$arrivalD,'and', $departureD)
                ->setFrom('viatores10@gmail.com')
                ->setTo($emailUser)
                ->setBody(
                    $this->renderView(
                        'backoffice/reservation/contact.html.twig',compact('new')
                    ),
                    'text/html'
                ) ;
    
        $mailer->send($message);
        

            $this->addFlash(
                'info',
                'Reservation Added successfully !'
            );



           // return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
            
        }

        return $this->render('/backoffice/reservation/new.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
            'qrCode' => $qrCode
        ]);
    }
    

    /**
     * @Route("/newfront", name="app_reservation_new_front", methods={"GET", "POST"})
     */
    public function newfront(Request $request, ReservationRepository $reservationRepository, CalenderroomRepository $CRepository,QrcodeService $qrcodeService  ): Response
    {
        $qrCode=null;
        $reservation = new Reservation();
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);

        $AvailableRoomOnDD=$CRepository->findOneBy(array('dateroom' => $reservation->getDeparturedate() ,
            'state'=>'0'));


        $AvailableRoomOnAD=$CRepository->findOneBy(array('dateroom' => $reservation->getArrivaldate() ,
            'state'=>'0' ));

        if ($form->isSubmitted() && $form->isValid() && $AvailableRoomOnDD!=NULL && $AvailableRoomOnAD!=NULL)
        {
            $reservationRepository->add($reservation);

            $this->addFlash(
                'info',
                'Your reservation is added successfully an Email will be sent!'
            );
            $dateString = $reservation->getArrivaldate()->format('d-m-Y H:i:s');
            $qrCode = $qrcodeService->qrcode( $dateString);
            //  return $this->redirectToRoute('app_reservation_show_front', [], Response::HTTP_SEE_OTHER);
        }
        if ($form->isSubmitted() && $form->isValid() && $AvailableRoomOnDD==NULL && $AvailableRoomOnAD==NULL)
        { $this->addFlash(
            'info',
            'SORRY no rooms are available for the given dates!'
        );}
        return $this->render('frontoffice/reservation/new.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
            'qrCode' => $qrCode
        ]);




    }
      

    /**
     * @Route("/{reservationid}", name="app_reservation_show_front", methods={"GET"})
     */
    public function showfront(Reservation $reservation): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        return $this->render('backoffice/reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }






    /**
     * @Route("/{reservationid}", name="app_reservation_show", methods={"GET"})
     */
    public function show(Reservation $reservation): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        return $this->render('backoffice/reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    /**
     * @Route("/{reservationid}/edit", name="app_reservation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reservation $reservation, EntityManagerInterface $entityManager): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        $form = $this->createForm(ReservationType::class, $reservation);
        $form->handleRequest($request);
         
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            $this->addFlash(
                'info',
                'Reservation updated successfully !'
            );
             
            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{reservationid}", name="app_reservation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reservation $reservation, EntityManagerInterface $entityManager,ProgressroomRepository $progressroomRepository): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("RECEPTIONIST",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        if ($this->isCsrfTokenValid('delete'.$reservation->getReservationid(), $request->request->get('_token'))) {
          
            $progress=$progressroomRepository->find(1);
            $progress->setProgress( $progress->getProgress() - 0.1 );
            $entityManager->remove($reservation);
            $entityManager->flush();
             

        }

        return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
    }

}
    