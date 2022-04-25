<?php

namespace App\Controller;

use App\Entity\Platter;
use App\Form\PlatterType;
use App\Repository\PlatterRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/platter")
 */
class PlatterController extends AbstractController
{
    /**
     * @Route("/", name="app_platter_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $platters = $entityManager
            ->getRepository(Platter::class)
            ->findAll();

        return $this->render('backoffice/platter/index.html.twig', [
            'platters' => $platters,
        ]);
    }

    /**
     * @Route("/new", name="app_platter_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $platter = new Platter();
        $form = $this->createForm(PlatterType::class, $platter);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
           $file=$platter->getImageplatter();
           $fileName= md5(uniqid()).'.'.$file->guessExtension();
           try{
               $file->move(
                   $this->getParameter('images_directory'),
                   $fileName
               );
           }catch(FileException $e) {

           }
           $platter->setImageplatter($fileName);
            $entityManager->persist($platter);
            $entityManager->flush();

            return $this->redirectToRoute('app_platter_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/platter/new.html.twig', [
            'platter' => $platter,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idplatter}", name="app_platter_show", methods={"GET"})
     */
    public function show(Platter $platter): Response
    {
        return $this->render('backoffice/platter/show.html.twig', [
            'platter' => $platter,
        ]);
    }

    /**
     * @Route("/{idplatter}/edit", name="app_platter_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Platter $platter, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PlatterType::class, $platter);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file=$platter->getImageplatter();
            $fileName= md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('images_directory'),
                    $fileName
                );
            }catch(FileException $e) {
 
            }
            $platter->setImageplatter($fileName);
            $entityManager->flush();
            $this->addFlash('success','Updated successfully !');
            return $this->redirectToRoute('app_platter_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/platter/edit.html.twig', [
            'platter' => $platter,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idplatter}", name="app_platter_delete", methods={"POST"})
     */
    public function delete(Request $request, Platter $platter, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$platter->getIdplatter(), $request->request->get('_token'))) {
            $entityManager->remove($platter);
            $entityManager->flush();
            $this->addFlash('success','Deleted successfully !');
        }

        return $this->redirectToRoute('app_platter_index', [], Response::HTTP_SEE_OTHER);
    }

 /**
     * @Route("platter/search", name="search")
     */
    public function search(PlatterRepository $repository,Request $request)
    {
        $data=$request->get('search');
        $platter=$repository->findBy(['nameplatter'=>$data]);
        return $this->render('backoffice/platter/index.html.twig', [
            'platters' => $platter, //kkk
        ]);
    }

}
