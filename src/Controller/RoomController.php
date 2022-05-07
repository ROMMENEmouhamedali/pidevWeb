<?php

namespace App\Controller;

use App\Entity\Room;

use App\Repository\RoomRepository;
use App\Entity\PropertySearch;
use App\Form\RoomType;
use App\Form\PropertySearchType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


/**
 * @Route("/room")
 */
class RoomController extends AbstractController
{
    /**
     * @Route("/", name="app_room_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager):Response
    {

        $rooms = $entityManager
        ->getRepository(Room::class)
        ->findAll();

    return $this->render('backoffice/room/index.html.twig', [
        'rooms' => $rooms,
    ]);

      
    }
    /**
     * @Route("room/search", name="search")
     */
    public function search(RoomRepository $repository,Request $request)
    { 
       $number=$request->get('search');
       $room=$repository->findBy(['roomnumber'=>$number]);
       return $this->render('backoffice/room/index.html.twig', 
       ['rooms' => $room, ]);
    }
    public function searchRoomNumber(RoomRepository $repository,Request $request)
    { 
       $number=$request->get('search');
       $room=$repository->findOneBy(['roomnumber'=>$number]);
       if($room!="")
       return true;
       else return false;
    }

    

    /**
     * @Route("/new", name="app_room_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,RoomRepository $repository): Response
    {  
        $room = new Room();


        $form = $this->createForm(RoomType::class, $room);
        $form->handleRequest($request);

        


        $roomtest=$repository->findOneBy(['roomnumber'=>$room->getRoomnumber()]);
      // var_dump($roomtest);
        if ($form->isSubmitted() && $form->isValid()&& $roomtest==NULL) {
            $entityManager->persist($room);
            $entityManager->flush();
            $this->addFlash(
                'info',
                'Room added successfully !'
            );
            return $this->redirectToRoute('app_room_index', [], Response::HTTP_SEE_OTHER);
        }
        
        return $this->render('backoffice/room/new.html.twig', [
            'room' => $room,
            'form' => $form->createView(),
        ]);
       
        

    
    }

    /**
     * @Route("/{roomid}", name="app_room_show", methods={"GET"})
     */
    public function show(Room $room): Response
    {
        return $this->render('backoffice/room/show.html.twig', [
            'room' => $room,
        ]);
    }

    /**
     * @Route("/{roomid}/edit", name="app_room_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Room $room, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(RoomType::class, $room);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();
            $this->addFlash(
                'info',
                'Room updated successfully !'
            );

            return $this->redirectToRoute('app_room_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/room/edit.html.twig', [
            'room' => $room,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{roomid}", name="app_room_delete", methods={"POST"})
     */
    public function delete(Request $request, Room $room, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$room->getRoomid(), $request->request->get('_token'))) {
            $entityManager->remove($room);
            $entityManager->flush(); 
        }
        
        return $this->redirectToRoute('app_room_index', [], Response::HTTP_SEE_OTHER);
    }
}
