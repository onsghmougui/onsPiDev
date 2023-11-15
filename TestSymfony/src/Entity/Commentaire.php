<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use DateTime;
use App\Repository\CommentaireRepository;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: CommentaireRepository::class)]
class Commentaire
{
    
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    
    #[ORM\Column]
    private ?int $iduser = null;

    
    #[ORM\Column]
    private ? int $idformation = null;

    #[Assert\NotBlank(message:"Text can't be empty")]
    #[ORM\Column(length: 1000)]
    private ?string $text = null;

   
    #[ORM\Column]
    private ?DateTime $date = null;

    #[Assert\NotBlank(message:" Evaluation can't be null")]
    #[ORM\Column]
    private ?int $evaluation = null;

    #[ORM\ManyToOne( inversedBy: "commentaires")]
    #[ORM\JoinColumn(nullable: true)]
    private ?Formation $formation = null;


    public function getId(): ?string
    {
        return $this->id;
    }

    public function getIduser(): ?string
    {
        return $this->iduser;
    }

    public function setIduser(string $iduser): static
    {
        $this->iduser = $iduser;

        return $this;
    }

    public function getIdformation(): ?string
    {
        return $this->idformation;
    }

    public function setIdformation(string $idformation): static
    {
        $this->idformation = $idformation;

        return $this;
    }

    public function getText(): ?string
    {
        return $this->text;
    }

    public function setText(string $text): static
    {
        $this->text = $text;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): static
    {
        $this->date = $date;

        return $this;
    }

    public function getEvaluation(): ?int
    {
        return $this->evaluation;
    }

    public function setEvaluation(int $evaluation): static
    {
        $this->evaluation = $evaluation;

        return $this;
    }
    public function getFormation()
    {
        return $this->formation;
    }

    public function setFormation(Formation $formation = null)
    {
        $this->formation = $formation;

        return $this;
    }


}
