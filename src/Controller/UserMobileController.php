<?php

namespace App\Controller;


use App\Entity\Authentication;
use App\Entity\User;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
class UserMobileController extends AbstractController
{
    /**
     * @Route("/userlist",name="userlist")
     */

    public function getUsers(UserRepository $repo,serializerInterface $serializer ){
        $users=$repo->findAll();
        $json=$serializer->serialize($users,'json',['groups'=>'user']);
        return new Response($json);
    }
    /**
     * @Route("/currentUser",name="currentLoggedIn")
     */
    public function getCurrentUser(UserRepository $userRepo, serializerInterface $serializer){
        $userLoggedIn  = $this->get('security.token_storage')->getToken()->getUser();

        $data = $serializer->serialize($userLoggedIn,"json");
        return $this->$data;
    }
    /**
     * @Route("/registeruser", name="registeruser")
     */
    public function registerUser( Request $request,SerializerInterface $serializer,
                                  UserPasswordEncoderInterface $encoder,
                                  EntityManagerInterface $manager){
        $user = new User();


        $user->setEmail($request->query->get("email"));
        $user->setPassword($encoder->encodePassword($user,$request->query->get("password")));
        $user->setLastname($request->query->get("lastname"));
        $user->setFirstname($request->query->get("firstname"));

        $user->setPhonenumber(00000);
        $user->setBlock(0);

        $user->setRoles(["ROLE_USER"]);
        $manager->persist($user);
        $manager->flush();
        return new Response($user);
    }
    /**
     * @Route("/apiget", name="apiget")
     */
    public function expose(User $user ,
                           Authentication $data,
                           serializerInterface $serializer){
        if($data->getPassword()===$user->getPassword())
        {
            $json = $serializer->serialize($user,'json');
            return new Response($json);
        }
        return new Response("user not found");
    }

    /**
     * @Route("/loginuser", name="login_user")
     * @Method("POST")
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function loginapi( UserRepository $repo,
                              Request $request,
                              serializerInterface $serializer,
                              UserPasswordEncoderInterface $encoder
    ){


        $user=new User();

        $hash =$encoder->encodePassword($user,$request->query->get("password"));
        $user->setPassword($hash);
        $user=$this->getDoctrine()->getRepository(User::class)->findOneBy(array('email'=>$request->query->get("login")));
        $userCheck = $this->getDoctrine()->getRepository(User::class)->findOneBy(array('password'=>$user->getPassword()));

        if($encoder->isPasswordValid($user,$request->query->get("password"))) {
            $json = $serializer->serialize($user, 'json');
            return new Response($json);
        }

        return new Response('Bad credentials');

    }
    /**
     * @Route("/updateuser", name="updateuser")
     */
    public function updateuser( UserRepository $repo,
                                Request $request,
                                serializerInterface $serializer,
                                EntityManagerInterface $entityManager

    ){

        $users=$repo->find($request->query->get("firstname"));


        $users->setFirstname($request->query->get("firstname"));
        $users->setLastname($request->query->get("lastname"));
        $users->setPhonenumber($request->query->get("phonenumber"));
        $entityManager->persist($users);
        $entityManager->flush();


        return new Response($users);

    }
    /**
     * @Route("/deleteuser", name="deleteuer")
     */
    public function deleteUser( UserRepository $repo,
                                Request $request,
                                serializerInterface $serializer,
                                EntityManagerInterface $entityManager

    ){



        $users=$repo->find($request->query->get("id"));
        $entityManager->remove($users);
        $entityManager->flush();
        return new Response("success");
    }



}