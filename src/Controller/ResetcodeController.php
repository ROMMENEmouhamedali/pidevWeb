<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ResetcodeController extends AbstractController
{
    /**
     * @Route("/resetcode", name="app_resetcode")
     */
    public function index(): Response
    {
        return $this->render('resetcode/index.html.twig', [
            'controller_name' => 'ResetcodeController',
        ]);
    }
}
