<?php

namespace App\Controller;
use App\Entity\Product;
use App\Repository\ProductRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;

use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Serializer;

class ProductmobileController extends AbstractController
{
   




 /**
     * @Route("/display", name="affichProd")
     */
    public function display() {
        $em= $this->getDoctrine()->getManager();
        $prod = $em->getRepository(Product::class)->findAll();

        //RESPONSE JSON FROM OUR SERVER
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();

        //JOIN ERROR
        $normalizer->setCircularReferenceLimit(1);
        $normalizer->setCircularReferenceHandler(function ($object) {
            if(method_exists($object, 'getIdProduct')){
                return $object->getIdProduct();
            }
        });


          $serializer = new Serializer([$normalizer],[$encoder]);
        $formatted = $serializer->normalize($prod);


        return new JsonResponse($formatted);


    }

/**
     * @Route("/delete", name="app_mobile_delete")
     *
     */
    public function delete(Request $request)
    {
       $idProduct=$request->get("idproduct");
       $em =$this->getDoctrine()->getManager();
       $products=$em->getRepository(Product::class)->find($idProduct);
       if($products!=null){
               $em->remove($products);
               $em->flush();
               $serialize= new Serializer([new ObjectNormalizer()]);
               $formatted=$serialize->normalize("product is deleted.");
               return new JsonResponse($formatted);
       }

        return new JsonResponse("id product is invalid");


    }

    
    /**
     * @Route("/update", name="app_mobile_update")
     *
     */
    public function update(Request $request, NormalizerInterface $normalizer)
    {

        $em =$this->getDoctrine()->getManager();
        $products=$this->getDoctrine()->getManager()->getRepository(Product::class)
            ->find($request->get("IdProduct"));
        $products->setRefproduct($request->get("RefProduct"));
        $products->setSuppliername($request->get("Suppliername"));
        $products->setUnitpriceproduct($request->get("Unitpriceproduct"));
        $products->setQuantityproduct($request->get("Quantityproduct"));

        $em->flush();

        return new Response("Updated Successfully");





    }

     /**
     * @Route("/addProduct", name="app_mobile_add")
     */
    public function add(Request $request, NormalizerInterface $normalizer,\Swift_Mailer $mailer){
        $em =$this->getDoctrine()->getManager();
        $products= new Product();
        $products->setRefproduct($request->get("RefProduct"));
        $products->setSuppliername($request->get("Suppliername"));
        $products->setUnitpriceproduct($request->get("Unitpriceproduct"));
        $products->setQuantityproduct($request->get("Quantityproduct"));
        $mail=[];
        $msg= $products->getRefproduct();
    
            $message = (new \Swift_Message("New product is added with a name  : ".$msg))
    
                ->setFrom('viatores10@gmail.com')
                ->setTo('zaghouani.ahlem@esprit.tn')
                ->setBody(
                   "test123"
                ) ;
    
        $mailer->send($message);
           
        $em->persist($products);
        $em->flush();

        return new Response("Product added");

    }

    /**
     * @Route("/filter", name="code_filter", methods={"GET"})
     */
    public function showMobileByTitle(ProductRepository $productRepository, Request $request): Response
    {
        $title = $request->query->get("RefProduct");
        $products = $productRepository->findBy(
            array(
                'RefProduct' => $title
            )   );
        $data = array();

        foreach ($products as $key => $prod){
            $data[$key]['title'] = $prod->getTitle();
            $data[$key]['Unitpriceproduct'] = $prod->getPrice();

        }
        return new JsonResponse($data);
    }

    
 

}


