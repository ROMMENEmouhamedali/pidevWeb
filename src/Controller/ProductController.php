<?php

namespace App\Controller;

use App\Entity\Product;
use App\Form\ProductType;
use knp\Component\Pager\PaginatorInterface;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
//Helooooo
/**
 * @Route("/product")
 */
class ProductController extends AbstractController
{
    /**
     * @Route("/", name="app_product_index", methods={"GET","POST"})
     */
    public function index(EntityManagerInterface $entityManager,Request $request): Response
    {
        $products = $entityManager
            ->getRepository(Product::class)
            ->findAll();
            if ($request->isMethod("POST") ) {

                $type= $request->get('sort') ;
                if ($type == "name" ) {
                    $products = $this->getDoctrine()->getRepository(Product::class)->findBy(array(),array('suppliername' => 'ASC'));
                }
                elseif ($type == "nbr_exp") {
                    $products = $this->getDoctrine()->getRepository(Product::class)->findBy(array(),array('unitpriceproduct' => 'DESC'));
                }
                elseif ($type == "nbr_exp") {
                    $products = $this->getDoctrine()->getRepository(Product::class)->findBy(array(),array('quantityproduct' => 'DESC'));
                }
                else {
                    $products = $this->getDoctrine()->getRepository(Product::class)->findAll();
                }
    
            }
    
           
        return $this->render('backoffice/product/index.html.twig', [
            'products' => $products,
        ]);
    }
     /**
     * @Route("/listp", name="product_list", methods={"GET"})
     */
    public function listp(ProductRepository $productsRepository): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('backoffice/product/listp.html.twig', [
            'products' => $productsRepository->findAll(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render('backoffice/product/index.html.twig');

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);


        // Send some text response
        return new Response("The PDF file has been succesfully generated !");
    }

    /**
     * @Route("/new", name="app_product_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager,\Swift_Mailer $mailer): Response
    {
        $product = new Product();
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $new=$form->getData();
            $entityManager->persist($product);
            $entityManager->flush();

            $mail=[];


            $msg= $product->getRefproduct();
    
            $message = (new \Swift_Message("New product is added with a name  : ".$msg))
    
                ->setFrom('viatores10@gmail.com')
                ->setTo('zaghouani.ahlem@esprit.tn')
                ->setBody(
                    $this->renderView(
                        'backoffice/product/contact.html.twig',compact('new')
                    ),
                    'text/html'
                ) ;
    
        $mailer->send($message);
            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/product/new.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idproduct}", name="app_product_show", methods={"GET"})
     */
    public function show(Product $product): Response
    {
        return $this->render('backoffice/product/show.html.twig', [
            'product' => $product,
        ]);
    }

    /**
     * @Route("/{idproduct}/edit", name="app_product_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Product $product, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('backoffice/product/edit.html.twig', [
            'product' => $product,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idproduct}", name="app_product_delete", methods={"POST"})
     */
    public function delete(Request $request, Product $product, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$product->getIdproduct(), $request->request->get('_token'))) {
            $entityManager->remove($product);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_product_index', [], Response::HTTP_SEE_OTHER);
    }

    

    
    

    
}
