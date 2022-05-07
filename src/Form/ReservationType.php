<?php

namespace App\Form;

use App\Entity\Reservation;
use App\Entity\User;
use App\Entity\Room;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;


class ReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('fkUserReservation',EntityType::class,[
            'class'=>User::class,
            'choice_label'=>'username',
            'multiple'=>false,
            'expanded'=>false,
              ])
            ->add('fkRoomReservation',EntityType::class,[
            'class'=>Room::class,
            'choice_label'=>'roomtype',
            'multiple'=>false,
            'expanded'=>false,
             ])
            ->add('arrivaldate')
            ->add('departuredate')
            ->add('specializedrequest')
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reservation::class,
        ]);
    }
}
