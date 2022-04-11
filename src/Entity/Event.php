<?php

namespace App\Entity;

use App\Repository\EventRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=EventRepository::class)
 */
class Event
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nameevent;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $priceevent;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $descriptionevent;

    /**
     * @ORM\Column(type="date")
     */
    private $startdateevent;

    /**
     * @ORM\Column(type="date")
     */
    private $enddateevent;

    /**
     * @ORM\Column(type="time")
     */
    private $starttimeevent;

    /**
     * @ORM\Column(type="time")
     */
    private $endtimeevent;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNameevent(): ?string
    {
        return $this->nameevent;
    }

    public function setNameevent(string $nameevent): self
    {
        $this->nameevent = $nameevent;

        return $this;
    }

    public function getPriceevent(): ?string
    {
        return $this->priceevent;
    }

    public function setPriceevent(string $priceevent): self
    {
        $this->priceevent = $priceevent;

        return $this;
    }

    public function getDescriptionevent(): ?string
    {
        return $this->descriptionevent;
    }

    public function setDescriptionevent(string $descriptionevent): self
    {
        $this->descriptionevent = $descriptionevent;

        return $this;
    }

    public function getStartdateevent(): ?\DateTimeInterface
    {
        return $this->startdateevent;
    }

    public function setStartdateevent(\DateTimeInterface $startdateevent): self
    {
        $this->startdateevent = $startdateevent;

        return $this;
    }

    public function getEnddateevent(): ?\DateTimeInterface
    {
        return $this->enddateevent;
    }

    public function setEnddateevent(\DateTimeInterface $enddateevent): self
    {
        $this->enddateevent = $enddateevent;

        return $this;
    }

    public function getStarttimeevent(): ?\DateTimeInterface
    {
        return $this->starttimeevent;
    }

    public function setStarttimeevent(\DateTimeInterface $starttimeevent): self
    {
        $this->starttimeevent = $starttimeevent;

        return $this;
    }

    public function getEndtimeevent(): ?\DateTimeInterface
    {
        return $this->endtimeevent;
    }

    public function setEndtimeevent(\DateTimeInterface $endtimeevent): self
    {
        $this->endtimeevent = $endtimeevent;

        return $this;
    }
}
