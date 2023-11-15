<?php

namespace App\Form;

use App\Entity\Commentaire;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class CommentaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            
            ->add('text', TextareaType::class, [
                
                'attr' => [
                'rows' => 5, // Nombre de lignes pour la zone de texte
                'placeholder' => 'Saisissez votre commentaire ici...',
                ],
            ])
            ->add('evaluation', ChoiceType::class,[
                'choices'=>['1' => '1',
                '2' => '2',
                '3' => '3',
                '4' => '4',
                '5' => '5'],]);
            
        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Commentaire::class,
        ]);
    }
}
