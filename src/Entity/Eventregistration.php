<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Eventregistration
 *
 * @ORM\Table(name="eventregistration")
 * @ORM\Entity
 */
class Eventregistration
{
    /**
     * @var int
     *
     * @ORM\Column(name="usersevents", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $usersevents;

    /**
     * @var int
     *
     * @ORM\Column(name="ID_EVENT", type="integer", nullable=false)
     */
    private $idEvent;

    /**
     * @var int
     *
     * @ORM\Column(name="ID_USER", type="integer", nullable=false)
     */
    private $idUser;

    public function getUsersevents(): ?int
    {
        return $this->usersevents;
    }

    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    public function setIdEvent(int $idEvent): self
    {
        $this->idEvent = $idEvent;

        return $this;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }


}
