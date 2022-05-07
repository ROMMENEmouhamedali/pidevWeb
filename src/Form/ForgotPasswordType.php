<?php

namespace App\Form;

use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Email;
use Symfony\Component\Validator\Constraints\NotBlank;


class ForgotPasswordType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    { $builder
        ->add('email',RepeatedType::class,[
            'type'=>EmailType::class,
            'invalid_message'=>"Email addresses must be identical",
            'required'=>true,
            'constraints'=>[
                new NotBlank(),
                new Email()
            ],
            'first_options'=>[
                'label'=>'Enter your email address'
            ],
            'second_options'=>[
                'label'=>'Confirm your e-mail address'
            ]
        ])
        ->add('CaptchaCode',CaptchaType::class,[
            'captchaConfig'=>'ExampleCaptchaUserRegistration',
            'constraints'=>[
                new ValidCaptcha([
                    'message'=>'Invalid captcha, Please try again'
                ])
            ]
        ])


    ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            // Configure your form options here
        ]);
    }
}
