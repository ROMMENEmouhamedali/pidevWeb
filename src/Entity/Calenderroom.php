<?php

namespace App\Entity;

use App\Repository\CalenderroomRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CalenderroomRepository::class)
 */
class Calenderroom
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date")
     */
    private $dateroom;

    /**
     * @ORM\Column(type="integer")
     */
    private $state;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $fkcalenderroom;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDateroom(): ?\DateTimeInterface
    {
        return $this->dateroom;
    }

    public function setDateroom(\DateTimeInterface $dateroom): self
    {
        $this->dateroom = $dateroom;

        return $this;
    }

    public function getState(): ?int
    {
        return $this->state;
    }

    public function setState(int $state): self
    {
        $this->state = $state;

        return $this;
    }

    public function getFkcalenderroom(): ?string
    {
        return $this->fkcalenderroom;
    }

    public function setFkcalenderroom(string $fkcalenderroom): self
    {
        $this->fkcalenderroom = $fkcalenderroom;

        return $this;
    }
}
