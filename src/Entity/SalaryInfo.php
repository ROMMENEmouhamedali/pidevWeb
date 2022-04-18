<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SalaryInfo
 *
 * @ORM\Table(name="salary_info")
 * @ORM\Entity
 */
class SalaryInfo
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID_SALARY", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idSalary;

    /**
     * @var int
     *
     * @ORM\Column(name="NB_WEEK", type="integer", nullable=false)
     */
    private $nbWeek;

    /**
     * @var float
     *
     * @ORM\Column(name="COST_N", type="float", precision=10, scale=0, nullable=false)
     */
    private $costN;

    /**
     * @var int
     *
     * @ORM\Column(name="EXTRA_H", type="integer", nullable=false)
     */
    private $extraH;

    /**
     * @var float
     *
     * @ORM\Column(name="COST_E_H", type="float", precision=10, scale=0, nullable=false)
     */
    private $costEH;

    public function getIdSalary(): ?int
    {
        return $this->idSalary;
    }

    public function getNbWeek(): ?int
    {
        return $this->nbWeek;
    }

    public function setNbWeek(int $nbWeek): self
    {
        $this->nbWeek = $nbWeek;

        return $this;
    }

    public function getCostN(): ?float
    {
        return $this->costN;
    }

    public function setCostN(float $costN): self
    {
        $this->costN = $costN;

        return $this;
    }

    public function getExtraH(): ?int
    {
        return $this->extraH;
    }

    public function setExtraH(int $extraH): self
    {
        $this->extraH = $extraH;

        return $this;
    }

    public function getCostEH(): ?float
    {
        return $this->costEH;
    }

    public function setCostEH(float $costEH): self
    {
        $this->costEH = $costEH;

        return $this;
    }


}
