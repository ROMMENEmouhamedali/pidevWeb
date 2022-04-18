<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Progressroom
 *
 * @ORM\Table(name="progressroom")
 * @ORM\Entity
 */
class Progressroom
{
    /**
     * @var int
     *
     * @ORM\Column(name="idProgress", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idprogress;

    /**
     * @var float
     *
     * @ORM\Column(name="progress", type="float", precision=10, scale=0, nullable=false)
     */
    private $progress;

    public function getIdprogress(): ?int
    {
        return $this->idprogress;
    }

    public function getProgress(): ?float
    {
        return $this->progress;
    }

    public function setProgress(float $progress): self
    {
        $this->progress = $progress;

        return $this;
    }


}
