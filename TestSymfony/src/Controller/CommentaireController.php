<?php

namespace App\Controller;

use App\Form\CommentaireType;
use App\Entity\Commentaire;
use App\Repository\CommentaireRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Formation;


class CommentaireController extends AbstractController
{
    #[Route('/commentaire', name: 'app_commentaire')]
    public function index(): Response
    {
        return $this->render('commentaire/index.html.twig', [
            'controller_name' => 'CommentaireController',
        ]);
    }
    #[Route('/addComm/{idFormation}', name: 'add_commentaire')]
    public function addCommentaire(ManagerRegistry $manager, Request $request,string $idFormation): Response
    {
        $em = $manager->getManager();
        $formation = $em->getRepository(Formation::class)->find($idFormation);
        if (!$formation) {
            throw $this->createNotFoundException('Formation not found for ID: $idFormation');
        }

        $commentaire = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $commentaire->setIdUser(1); // Assuming you have a User entity with getId()
            $commentaire->setFormation($formation);
            $commentaire->setIdFormation($idFormation);
            $commentaire->setDate(new \DateTime());
            $em->persist($commentaire);
            $em->flush();


            $formation = $commentaire->getFormation();
            // Récupérer tous les commentaires associés à la formation
             $commentaires = $em->getRepository(Commentaire::class)->findBy(['formation' => $formation]);

             return $this->renderForm('commentaire/tabbela.html.twig', [
                'formation' => $formation,
                'f' => $form,
                'commentaires' => $commentaires,]);
        }
        return $this->renderForm('commentaire/tabbela.html.twig', [
            'formation' => $formation,
            'f' => $form,
            'commentaires' => [],
        ]);
    }


    #[Route('/allcommentaire', name:'all_commentaire')]
    public function getAll(CommentaireRepository $repo):Response
    {
        $list=$repo->findAll();
        return $this->render('commentaire/getComm.html.twig',[
            'commentaires'=>$list
        ]);

    }

    #[Route('/updateComm/{id}', name: 'edit_commentaire')]
    public function updatecommentaire(ManagerRegistry $managerRegistry,$id,CommentaireRepository $repo,Request $req): Response
    {
        $em = $managerRegistry->getManager();

        $commentaire  = $repo->find($id);
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($req);

        if ($form->isSubmitted()) {
            $em->persist($commentaire);
            $em->flush();
            return $this->redirectToRoute('all_commentaire');
        }

        return $this->renderForm('commentaire/addcommentaire.html.twig', [
            'form' => $form
        ]);
    }

    #[Route('/deletecommentaire/{id}', name: 'delete_commentaire')]
    public function delete(CommentaireRepository $repo,ManagerRegistry $manager,$id):Response
    {
        $commentaire=$repo->find($id);
        $mr=$manager->getManager();
        $mr->remove($commentaire);
        $mr->flush();

        return $this->redirectToRoute('all_commentaire');

    }
    
  
}


