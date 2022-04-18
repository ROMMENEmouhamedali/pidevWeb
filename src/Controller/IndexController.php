<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class IndexController extends AbstractController
{
    /**
     * @Route("/index1", name="app_index1")
     */
    public function index1(): Response
    {
        return $this->render('frontoffice/index.html.twig');
    }

     /**
     * @Route("/index2", name="app_index2")
     */
    public function index2(): Response
    {
        return $this->render('backoffice/index.html.twig');
    }
}
