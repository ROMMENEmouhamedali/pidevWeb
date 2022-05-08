<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Vacationrequest
 *
 * @ORM\Table(name="vacationrequest")
 * @ORM\Entity
 */
class Vacationrequest
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="initial_date", type="string", length=255, nullable=false)
     */
    private $initialDate;

    /**
     * @var string
     *
     * @ORM\Column(name="final_date", type="string", length=255, nullable=false)
     */
    private $finalDate;

    /**
     * @var string
     *
     * @ORM\Column(name="context", type="string", length=255, nullable=false)
     */
    private $context;

    /**
     * @var string
     *
     * @ORM\Column(name="reason", type="string", length=255, nullable=false)
     */
    private $reason;

    /**
     * @var bool
     *
     * @ORM\Column(name="state", type="boolean", nullable=false)
     */
    private $state;

    /**
     * @var int
     *
     * @ORM\Column(name="ID_User", type="integer", nullable=false, options={"default"="2"})
     */
    private $idUser = 2;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getInitialDate(): ?string
    {
        return $this->initialDate;
    }

    public function setInitialDate(string $initialDate): self
    {
        $this->initialDate = $initialDate;

        return $this;
    }

    public function getFinalDate(): ?string
    {
        return $this->finalDate;
    }

    public function setFinalDate(string $finalDate): self
    {
        $this->finalDate = $finalDate;

        return $this;
    }

    public function getContext(): ?string
    {
        return $this->context;
    }

    public function setContext(string $context): self
    {
        $this->context = $context;

        return $this;
    }

    public function getReason(): ?string
    {
        return $this->reason;
    }

    public function setReason(string $reason): self
    {
        $this->reason = $reason;

        return $this;
    }

    public function getState(): ?bool
    {
        return $this->state;
    }

    public function setState(bool $state): self
    {
        $this->state = $state;

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
