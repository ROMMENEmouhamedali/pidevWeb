<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Event
 *
 * @ORM\Table(name="event")
 * @ORM\Entity
 */
class Event
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_EVENT", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="NAME_EVENT", type="string", length=50, nullable=false)
     */
    private $nameEvent;

    /**
     * @var int
     *
     * @ORM\Column(name="PRICE_EVENT", type="integer", nullable=false)
     */
    private $priceEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="DESCRIPTION_EVENT", type="string", length=500, nullable=false)
     */
    private $descriptionEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="STARTDATE_EVENT", type="date", nullable=false)
     */
    private $startdateEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="ENDDATE_EVENT", type="date", nullable=false)
     */
    private $enddateEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="STARTTIME_EVENT", type="time", nullable=false)
     */
    private $starttimeEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="ENDTIME_EVENT", type="time", nullable=false)
     */
    private $endtimeEvent;

    /**
     * @var int
     *
     * @ORM\Column(name="FK_ID_USER", type="integer", nullable=false)
     */
    private $fkIdUser;

    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    public function getNameEvent(): ?string
    {
        return $this->nameEvent;
    }

    public function setNameEvent(string $nameEvent): self
    {
        $this->nameEvent = $nameEvent;

        return $this;
    }

    public function getPriceEvent(): ?int
    {
        return $this->priceEvent;
    }

    public function setPriceEvent(int $priceEvent): self
    {
        $this->priceEvent = $priceEvent;

        return $this;
    }

    public function getDescriptionEvent(): ?string
    {
        return $this->descriptionEvent;
    }

    public function setDescriptionEvent(string $descriptionEvent): self
    {
        $this->descriptionEvent = $descriptionEvent;

        return $this;
    }

    public function getStartdateEvent(): ?\DateTimeInterface
    {
        return $this->startdateEvent;
    }

    public function setStartdateEvent(\DateTimeInterface $startdateEvent): self
    {
        $this->startdateEvent = $startdateEvent;

        return $this;
    }

    public function getEnddateEvent(): ?\DateTimeInterface
    {
        return $this->enddateEvent;
    }

    public function setEnddateEvent(\DateTimeInterface $enddateEvent): self
    {
        $this->enddateEvent = $enddateEvent;

        return $this;
    }

    public function getStarttimeEvent(): ?\DateTimeInterface
    {
        return $this->starttimeEvent;
    }

    public function setStarttimeEvent(\DateTimeInterface $starttimeEvent): self
    {
        $this->starttimeEvent = $starttimeEvent;

        return $this;
    }

    public function getEndtimeEvent(): ?\DateTimeInterface
    {
        return $this->endtimeEvent;
    }

    public function setEndtimeEvent(\DateTimeInterface $endtimeEvent): self
    {
        $this->endtimeEvent = $endtimeEvent;

        return $this;
    }

    public function getFkIdUser(): ?int
    {
        return $this->fkIdUser;
    }

    public function setFkIdUser(int $fkIdUser): self
    {
        $this->fkIdUser = $fkIdUser;

        return $this;
    }


}
