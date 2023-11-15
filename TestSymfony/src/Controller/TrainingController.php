<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\FormationRepository;
use App\Service\FileUploader;
use App\Form\FormationType;
use App\Entity\Formation;

class TrainingController extends AbstractController
{
    #[Route('/training', name: 'app_training')]
    public function index(): Response
    {
        return $this->render('training/index.html.twig', [
            'controller_name' => 'TrainingController',
        ]);
    }
    #[Route('/train' , name:"training" )]
    public function shop(FormationRepository $formationRepository) {
        $formation=$formationRepository->findAll();
        return $this->render('training/train.html.twig' ,[
            'training'=>$formation,
        ]);
    }
}
