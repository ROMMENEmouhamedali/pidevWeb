<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AccessController extends AbstractController
{
    /**
     * @Route("/access", name="app_access")
     */
    public function index(): Response
    {
        return $this->render('access/error404.html.twig', [
            'controller_name' => 'AccessController',
        ]);
    }
    /**
     * @Route("/accesseventmanager", name="app_access_event_manager")
     */
    public function acessEventManager(): Response
    {
        return $this->render('access/accesseventmanager.html.twig', [
            'controller_name' => 'AccessController',
        ]);
    }
}
