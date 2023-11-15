<?php

namespace App\Form;

use App\Entity\Formation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\Unique;
use Symfony\Component\Validator\Constraints\Type;

class FormationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('titre', null, [
            'attr' => ['placeholder' => 'Title'],
            'label' => false,
            'constraints' => [
                new NotBlank(['message' => 'Please enter a title.']),
                new Length([
                    'min' => 3,
                    'max' => 255,
                    'minMessage' => 'Your title should be at least {{ limit }} characters.',
                    'maxMessage' => 'Your title should be no longer than {{ limit }} characters.',
                ]),
                new Unique([
                    'message' => 'This title is already in use.',
                    'entityClass' => YourEntity::class, // Replace with your actual entity class
                    'field' => 'titre',
                ]),
            ],
        ])

        ->add('categories', ChoiceType::class,[
            'choices'=>['Kitshen' => 'kitshen',
            'Tapestry' => 'tapestry',
            'Jewelry' => 'jewelry',
            'Pottery' => 'pottery',],], [
            'attr' => ['placeholder' => 'Categories'],
            'label' => false,])
        
            ->add('prix', null, [
                'attr' => ['placeholder' => 'Price'],
                'label' => false,
                'constraints' => [
                    new NotBlank(['message' => 'Please enter a price.']),
                    new Type([
                        'type' => 'float',
                        'message' => 'Price should be a valid number.',
                    ]),]])
        ->add('remise', null, [
            'attr' => ['placeholder' => 'Discount'],
            'label' => false,])
        ->add('duree', null, [
            'attr' => ['placeholder' => 'Duration'],
            'label' => false,])
        ->add('description', null, [
            'attr' => ['placeholder' => 'Description'],
            'label' => false,])
        ->add('video', FileType::class, [
            'mapped' => false, // Pour indiquer que ce champ ne sera pas mappé à une entité Doctrine
            'required' => false, // Si le champ est facultatif
            'attr' => ['accept' => '.mp4'], // Spécifiez les types de fichiers acceptés (seulement les fichiers MP4)
            'label' => false,])
        ->add('Save',SubmitType::class)
    ;
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Formation::class,
        ]);
    }
}
