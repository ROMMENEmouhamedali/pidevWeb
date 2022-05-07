<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ForgotPasswordType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class ForgotPasswordController extends AbstractController
{
    /**
     * @Route("/forgot/password", name="app_forgot_password")
     */
    public function index(Request $request, \Swift_Mailer $mailer,UserPasswordEncoderInterface $encoder)
    {
        $form = $this->createForm(ForgotPasswordType::class);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();
        $user=new User();
        $user = $em->getRepository(User::class)->findOneByEmail($form["email"]->getData());
        if ($form->isSubmitted() && $form->isValid()) {
            $message=(new \Swift_Message('forgot password'));
            $message->setFrom("mouhamedali.rommene@esprit.tn");
            $message->setTo($form["email"]->getData());
            $length = 12;
            $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
            $charactersLength = strlen($characters);
            $randomString = '';
            for ($i = 0; $i < $length; $i++) {
                $randomString .= $characters[rand(0, $charactersLength - 1)];
            }
            $message->setBody("Votre code est : " .$randomString);
            ;
            $user->setResetCode($randomString);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();
            $mailer->send($message);
            $this->addFlash('message','le message a ete envoie');

            return $this->redirectToRoute('app_reset_password');
        }

        return $this->render('forgot_password/index.html.twig', [

            'form' => $form->createView(),
        ]);
    }

}
