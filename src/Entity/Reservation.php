<?php

namespace App\Entity;
use App\Entity\User;
use App\Entity\Room;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\ReservationRepository;

/**
 * Reservation
 *
 * @ORM\Table(name="reservation", indexes={@ORM\Index(name="fk_room_reservation", columns={"fk_room_reservation"}), @ORM\Index(name="fk_user_reservation", columns={"fk_user_reservation"})})
 * @ORM\Entity(repositoryClass=ReservationRepository::class)
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="reservationId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reservationid;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="arrivalDate", type="date", nullable=false)
     */
    private $arrivaldate;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="departureDate", type="date", nullable=false)
     */
    private $departuredate;

    /**
     * @var string
     *
     * @ORM\Column(name="specializedRequest", type="string", length=255, nullable=false)
     */
    private $specializedrequest;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User", cascade={"all"})
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_user_reservation", referencedColumnName="id")
     * })
     */
    private $fkUserReservation;

    /**
     * @var \Room
     *
     * @ORM\ManyToOne(targetEntity="Room", cascade={"all"})
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fk_room_reservation", referencedColumnName="roomId")
     * })
     */
    private $fkRoomReservation;

    public function getReservationid(): ?int
    {
        return $this->reservationid;
    }

    public function getArrivaldate(): ?\DateTimeInterface
    {
        return $this->arrivaldate;
    }

    public function setArrivaldate(\DateTimeInterface $arrivaldate): self
    {
        $this->arrivaldate = $arrivaldate;

        return $this;
    }

    public function getDeparturedate(): ?\DateTimeInterface
    {
        return $this->departuredate;
    }

    public function setDeparturedate(\DateTimeInterface $departuredate): self
    {
        $this->departuredate = $departuredate;

        return $this;
    }

    public function getSpecializedrequest(): ?string
    {
        return $this->specializedrequest;
    }

    public function setSpecializedrequest(string $specializedrequest): self
    {
        $this->specializedrequest = $specializedrequest;

        return $this;
    }

    public function getFkUserReservation(): ?User
    {
        return $this->fkUserReservation;
    }

    public function setFkUserReservation(?User $fkUserReservation): self
    {
        $this->fkUserReservation = $fkUserReservation;

        return $this;
    }

    public function getFkRoomReservation(): ?Room
    {
        return $this->fkRoomReservation;
    }

    public function setFkRoomReservation(?Room $fkRoomReservation): self
    {
        $this->fkRoomReservation = $fkRoomReservation;

        return $this;
    }


}
