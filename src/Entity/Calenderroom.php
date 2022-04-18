<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Calenderroom
 *
 * @ORM\Table(name="calenderroom", indexes={@ORM\Index(name="fkCalenderRoom", columns={"fkCalenderRoom"})})
 * @ORM\Entity
 */
class Calenderroom
{
    /**
     * @var int
     *
     * @ORM\Column(name="calenderRoomId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $calenderroomid;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateRoom", type="date", nullable=false)
     */
    private $dateroom;

    /**
     * @var int
     *
     * @ORM\Column(name="state", type="integer", nullable=false)
     */
    private $state;

    /**
     * @var \Room
     *
     * @ORM\ManyToOne(targetEntity="Room")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fkCalenderRoom", referencedColumnName="roomId")
     * })
     */
    private $fkcalenderroom;

    public function getCalenderroomid(): ?int
    {
        return $this->calenderroomid;
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

    public function getFkcalenderroom(): ?Room
    {
        return $this->fkcalenderroom;
    }

    public function setFkcalenderroom(?Room $fkcalenderroom): self
    {
        $this->fkcalenderroom = $fkcalenderroom;

        return $this;
    }


}
