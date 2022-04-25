<?php

namespace App\Controller;


use App\Form\ResetPassType;
use App\Form\UserType;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\User;
use App\Form\RegistrationType;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ObjectManager;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Security\Core\User\UserInterface;

class SecurityController extends AbstractController
{
    /**
    * @Route("/inscription", name="security_registration")
    */
  public function registration(Request $request, EntityManagerInterface $entityManager,UserPasswordEncoderInterface $userPasswordEncoder): Response
  {
      if ($this->getUser()) {
          return $this->redirectToRoute('app_home');
      }
        $user = new User();
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('password')->getData()
                )

            );
            $user->setRoles(["ROLE_USER"]);
            $entityManager->persist($user);
            $entityManager->flush();
            return $this->redirectToRoute('app_login');
        }
        return $this->render('security/registration.html.twig', [
            'form' => $form->createView()
        ]);
    }





}
