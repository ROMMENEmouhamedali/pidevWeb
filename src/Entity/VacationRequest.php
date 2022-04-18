<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * VacationRequest
 *
 * @ORM\Table(name="vacation_request")
 * @ORM\Entity
 */
class VacationRequest
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_EMPLOYEE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEmployee;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="initial_date", type="date", nullable=false)
     */
    private $initialDate;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="final_date", type="date", nullable=false)
     */
    private $finalDate;

    /**
     * @var string
     *
     * @ORM\Column(name="context", type="string", length=256, nullable=false)
     */
    private $context;

    /**
     * @var string
     *
     * @ORM\Column(name="reason", type="text", length=65535, nullable=false)
     */
    private $reason;

    /**
     * @var bool
     *
     * @ORM\Column(name="state", type="boolean", nullable=false)
     */
    private $state;

    public function getIdEmployee(): ?int
    {
        return $this->idEmployee;
    }

    public function getInitialDate(): ?\DateTimeInterface
    {
        return $this->initialDate;
    }

    public function setInitialDate(\DateTimeInterface $initialDate): self
    {
        $this->initialDate = $initialDate;

        return $this;
    }

    public function getFinalDate(): ?\DateTimeInterface
    {
        return $this->finalDate;
    }

    public function setFinalDate(\DateTimeInterface $finalDate): self
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


}
