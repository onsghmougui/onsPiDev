<?php

namespace App\Controller;

use App\Form\FormationType;
use App\Entity\Formation;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\FormationRepository;
use App\Service\FileUploader;


class FormationController extends AbstractController
{
    #[Route('/formation', name: 'app_formation')]
    public function index(): Response
    {
        return $this->render('formation/index.html.twig', [
            'controller_name' => 'FormationController',
        ]);
    }
    #[Route('/addformation', name: 'add_formation')]
    public function addformation(ManagerRegistry $managerRegistry,Request $req, FileUploader $fileUploader): Response
    {
        $em = $managerRegistry->getManager();
        $formation = new Formation();
        $form = $this->createForm(FormationType::class,   $formation);
        $form->handleRequest($req);
        if ($form->isSubmitted() and $form->isValid()) {
            $videoFile = $form->get('video')->getData(); // Remplacez 'video' par le nom de votre champ de fichier vidéo
            if ($videoFile) {
                $videoFileName = $fileUploader->upload($videoFile);
                $formation->setVideo($videoFileName);
                
            }
            $em->persist($formation);
            $em->flush();
            //return new Response("formation ajoutée avec succes");
            return $this->redirectToRoute('all_formation');
        }   

        return $this->renderForm('formation/addformation.html.twig', [
            'f' => $form
        ]);
    }
    
    #[Route('/updateformation/{id}', name: 'update_formation')]
    public function updateformation(ManagerRegistry $managerRegistry,$id,FormationRepository $repo,Request $req, FileUploader $fileUploader): Response
    {
        $em = $managerRegistry->getManager();
        $formation = $repo->find($id);
        $form = $this->createForm(FormationType::class,   $formation);
        $form->handleRequest($req);
        if ($form->isSubmitted() and $form->isValid()) {
            $videoFile = $form->get('video')->getData(); // Remplacez 'video' par le nom de votre champ de fichier vidéo
            if ($videoFile) {
                $videoFileName = $fileUploader->upload($videoFile);
                $formation->setVideo($videoFileName);
            }
            $em->persist($formation);
            $em->flush();

            return $this->redirectToRoute('all_formation');
        }

        return $this->renderForm('formation/addformation.html.twig', [
            'f' => $form
        ]);
    }

    #[Route('/formation/{id}', name: 'one_formation')]
    public function getOne(FormationRepository $repo, $id):Response
    {
        $formation=$repo->find($id);

        return $this->render('formation/oneformation.html.twig',[
            'f'=>$formation
        ]);
    }


    #[Route('/allformation', name:'all_formation')]
    public function getAll(FormationRepository $repo):Response
    {
        $list=$repo->findAll();
        return $this->render('formation/getall.html.twig',[
            'formations'=>$list
        ]);

    }

    #[Route('/deleteformation/{id}', name: 'delete_formation')]
    public function delete(FormationRepository $repo,ManagerRegistry $manager,$id):Response
    {
        $formation=$repo->find($id);
        $mr=$manager->getManager();
        $mr->remove($formation);
        $mr->flush();

        return $this->redirectToRoute('all_formation');

    }

}
