<?php

namespace App\Controller;
use App\Entity\Progressroom;
use App\Repository\ProgressroomRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;


class ChartjsController extends AbstractController
{
    /**
     * @Route("/chartjs", name="app_chartjs")
     */
    public function index(ProgressroomRepository $progressroomRepository,ChartBuilderInterface $chartBuilder): Response
    {   

        $progressroomAll=$progressroomRepository->findAll();
         $labels=[];
         $data=[];
         foreach($progressroomAll as $progressroom){
           $labels[]=['niveau1','niveau2','niveau3'];
           $data[]=$progressroom-> getProgress();


         }
         $chart = $chartBuilder->createChart(Chart::TYPE_LINE);
        $chart->setData([
            'labels' => $labels,
            'datasets' => [
                [
                    'label' => 'My First dataset',
                    'backgroundColor' => 'rgb(255, 99, 132)',
                    'borderColor' => 'rgb(255, 99, 132)',
                    'data' => $data,
                ],
            ],
        ]);
        
        $chart->setOptions([]);


        return $this->render('backoffice/reservation/index.html.twig', [
            'controller_name' => 'ChartjsController',
            'chart' => $chart,
        ]);
    }
}
