<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\ProfileType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class ProfileController extends AbstractController
{
    /**
     * @Route("/profile", name="app_profile")
     */
    public function index(Request $request,UserPasswordEncoderInterface $encoder): Response
    {  if(!($this->getUser())){


        return $this->redirectToRoute('app_login');
    }
        $form = $this->createForm(ProfileType::class);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();

        $user1=$this->getUser()->getId();
        $user = $em->getRepository(User::class)->find($user1);

        if ($form->isSubmitted() && $form->isValid()) {
            if ($encoder->isPasswordValid($user, $form["confirm"]->getData())) {
                if($form["newPassword"]->getData()) {
                    $user->setPassword($encoder->encodePassword($user, $form["newPassword"]->getData()));
                }
                if($form["firstname"]->getData()) {
                    $user->setFirstname($form["firstname"]->getData());
                }
                if($form["lastname"]->getData()) {
                    $user->setLastname($form["lastname"]->getData());
                }
                if($form["phonenumber"]->getData()) {
                    $user->setPhonenumber($form["phonenumber"]->getData());
                }
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($user);
                $entityManager->flush();
                return $this->redirectToRoute('app_event_index');


            }
            return $this->redirectToRoute('app_login');


        }
        return $this->render('profile/index.html.twig', ['registrationForm' => $form->createView(),
            'user'=>$this->getUser(),
        ]);
    }
}
