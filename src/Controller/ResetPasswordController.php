<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\resetPassword;
use App\Form\ResetPasswordType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class ResetPasswordController extends AbstractController
{
    /**
     * @Route("/reset/password", name="app_reset_password")
     */
    public function index(Request $request,UserPasswordEncoderInterface $encoder): Response
    {
        $form = $this->createForm(ResetPasswordType::class);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();


        $user = $em->getRepository(User::class)->findOneBy(['resetCode'=>$form["resetCode"]->getData()]);

        if ($form->isSubmitted() && $form->isValid()) {
            if ($user->getresetCode() == $form["resetCode"]->getData()) {
                $user->setPassword($encoder->encodePassword($user, $form["newPassword"]->getData()));

                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($user);
                $entityManager->flush();
                return $this->redirectToRoute('app_login');


            }
            return $this->redirectToRoute('login');


        }
        return $this->render('reset_password/index.html.twig', ['resetPassword' => $form->createView(),
        ]);
    }
}
