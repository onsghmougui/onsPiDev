<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\FormationRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;

#[ORM\Entity(repositoryClass: FormationRepository::class)]
class Formation
{
    
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    

    #[Assert\NotBlank(message:"Titre can't be empty")]
    #[ORM\Column(length: 150)]
    private ?string $titre = null;

    #[Assert\NotBlank(message:"Categories can't be empty")]
    #[ORM\Column(length: 150)]
    private ?string $categories = null;

    #[Assert\NotBlank(message:"Prix can't be null")]
    #[ORM\Column]
    private ?float $prix = null;

    
    #[ORM\Column]
    private ?float $remise = null;

    
    #[ORM\Column(length: 100)]
    private ?string $duree;

    #[Assert\NotBlank(message:"Description can't be empty")]
    #[ORM\Column(length: 1000)]
    private ?string $description = null ;

    //#[Assert\NotBlank(message:"Video can't be empty")]
    #[ORM\Column(length: 1000)]
    private ?string $video ;

   
    #[ORM\OneToMany( mappedBy: "formation",targetEntity: Commentaire::class)]
    private Collection $commentaires ;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): static
    {
        $this->titre = $titre;

        return $this;
    }

    public function getCategories(): ?string
    {
        return $this->categories;
    }

    public function setCategories(string $categories): static
    {
        $this->categories = $categories;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): static
    {
        $this->prix = $prix;

        return $this;
    }

    public function getRemise(): ?float
    {
        return $this->remise;
    }

    public function setRemise(float $remise): static
    {
        $this->remise = $remise;

        return $this;
    }

    public function getDuree(): ?string
    {
        return $this->duree;
    }

    public function setDuree(string $duree): static
    {
        $this->duree = $duree;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): static
    {
        $this->description = $description;

        return $this;
    }

    public function getVideo(): ?string
    {
        return $this->video;
    }

    public function setVideo(string $video): static
    {
        $this->video = $video;

        return $this;
    }
    public function __construct()
    {
        $this->commentaires = new ArrayCollection();

    }

    public function getCommentaires()
    {
        return $this->commentaires;
    }

   
    

    public function addCommentaire(Commentaire $commentaire)
    {
        if (!$this->commentaires->contains($commentaire)) {
            $this->commentaires->add($commentaire);
            $commentaire->setFormation($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire)
    {
        if ($this->commentaires->removeElement($commentaire)) {
            // set the owning side to null (unless already changed)
            if ($commentaire->getFormation() === $this) {
                $commentaire->setFormation(null);
            }
        }

        return $this;
    }


}
