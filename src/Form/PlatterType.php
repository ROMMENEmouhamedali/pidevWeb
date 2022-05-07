<?php

namespace App\Form;

use App\Entity\Platter;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Entity\Product;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\FileType ; 
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
class PlatterType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nameplatter')
            ->add('ingredient',EntityType::class,[
                'class'=>Product::class,
                'choice_label'=>'RefProduct',
                'multiple'=>false,
                'expanded'=>false,
                  ])
            ->add('priceplatter')
            ->add('nbplatter')
            ->add('descriptionplatter',TextareaType::class)
            ->add('typeplatter',ChoiceType::class,[
                'choices'  => [
                    'Breakfast' => 'Breakfast',
                    'Lunch' => 'Lunch',
                    'Dinner' => 'Dinner',
                ]
                
            ])
            ->add('imageplatter',FileType::class, array('data_class' => null))
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Platter::class,
        ]);
    }
}
