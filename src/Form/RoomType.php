<?php

namespace App\Form;

use App\Entity\Room;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class RoomType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('roomnumber')
            ->add('roomtype',ChoiceType::class, [
                'choices'  => [
                    'Single' => 'Single',
                    'Double' => 'Double',
                    'Triplet' => 'Triplet',
                    'Queen' => 'Queen',
                ],
            ])
            
               
            ->add('roompricepernight')
            ->add('roomdirection',ChoiceType::class, [
                'choices'  => [
                    '1st floor on the right ' => '1st floor on the right',
                    '1st floor on the left' => '1st floor on the left',
                    '2nd floor on the right' => '2nd floor on the right',
                    '2nd floor on the left' => '2nd floor on the left',
                    '3rd floor on the right' => '3rd floor on the right',
                    '3rd floor on the left' => '3rd floor on the left',
                ],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Room::class,
        ]);
    }
}
