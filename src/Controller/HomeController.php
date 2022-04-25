<?php

namespace App\Controller;

use App\Entity\Event;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{



    private $userRepository;
    public function __construct(UserRepository $userRepository)
    {
        $this->userRepository = $userRepository;

    }



    /**
     * @Route("/home", name="app_home")
     */
    public function index(): Response
    {
        return $this->render('base-front.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    /**
     * @Route("/admin", name="app_admin")
     */
    public function back(): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }
        if((!(in_array("ROLE_EVENT_MANAGER",$this->getUser()->getRoles())))||(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles())))) {
            return $this->redirectToRoute('app_home');
        }
        return $this->render('base-admin.html.twig', [
            'controller_name' => 'back',
        ]);
    }
    /**
     * @Route("/access", name="app_error")
     */
    public function error(): Response
    {
        return $this->render('access/error404.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }

    /**
     * @Route("/listevent", name="app_listevent")
     */
    public function displayEvent()
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_access');
        }

        $repo = $this->getDoctrine()->getRepository(Event::class);
        $event = $repo->findAll();
        return $this->render('front/displayevent.html.twig', [
            'controller_name' => 'HomeController',
            'events' => $event
        ]);
    }
    /**
     * @Route("/stat", name="app_stat")
     */
    public function statistique(): Response
    {
        return $this->render('user/stats.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }

    /**
     * @Route("/stat", name="app_stat")
     */
    public function statistiques(UserRepository $userRepository ){


        $users = $this->userRepository->findAll();
        $categNom = [];

        $categCount = [];
        $deletedCount= [];



           // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach($users as $user){
            $categNom[] = $user->getFirstname();
            // $categColor[] = $categorie->getColor();
            // $categCount[] = $user->getRoles();
            $categCount[] = $user->isvalid();
            $deletedCount[] = $user->isDeleted();



        }


        return $this->render("user/stats.html.twig", [
            'categNom' => json_encode($categNom),

            'categCount' => json_encode($categCount),
            'deletedCount' => json_encode($deletedCount),

        ]);
    }

}
