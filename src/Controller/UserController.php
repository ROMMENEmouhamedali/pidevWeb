<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\User1Type;
use App\Form\UserType;
use App\Repository\UserRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/user")
 */
class UserController extends AbstractController
{
    /**
     * @Route("/", name="app_user_index", methods={"GET"})
     */
    public function index(UserRepository $userRepository,Request $request,PaginatorInterface $paginator): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }
        if(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        $search = $request->query->get("search");
        $users = $userRepository->findAllWithSearch($search);
        //$users= $userRepository->findAll();

        return $this->render('user/index.html.twig', [
            'users' => $users,
        ]);
    }

    /**
     * @Route("/new", name="app_user_new", methods={"GET", "POST"})
     */
    public function new(Request $request, UserRepository $userRepository): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }

        if(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        $user = new User();
        $form = $this->createForm(User1Type::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $userRepository->add($user);
            return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/new.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {


        if(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_home');
        }
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, User $user, UserRepository $userRepository): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_login');
        }

        if(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $userRepository->add($user);
            return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="app_user_delete", methods={"POST"})
     */
    public function delete(Request $request, User $user, UserRepository $userRepository): Response
    {
        if (!($this->getUser())) {
            return $this->redirectToRoute('app_login');
        }

        if(!(in_array("ROLE_ADMIN",$this->getUser()->getRoles()))) {
            return $this->redirectToRoute('app_home');
        }
        if ($this->isCsrfTokenValid('delete' . $user->getId(), $request->request->get('_token'))) {
            $userRepository->remove($user);
        }

        return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
    }






}
