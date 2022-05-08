<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Salaryinfo
 *
 * @ORM\Table(name="salaryinfo")
 * @ORM\Entity
 */
class Salaryinfo
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
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false, options={"default"="4"})
     */
    private $idUser = 4;

    /**
     * @var int
     *
     * @ORM\Column(name="Nb_Week", type="integer", nullable=false)
     */
    private $nbWeek;

    /**
     * @var float
     *
     * @ORM\Column(name="Cost_W", type="float", precision=10, scale=0, nullable=false)
     */
    private $costW;

    /**
     * @var int
     *
     * @ORM\Column(name="Extra_H", type="integer", nullable=false)
     */
    private $extraH;

    /**
     * @var float
     *
     * @ORM\Column(name="Cost_E_H", type="float", precision=10, scale=0, nullable=false)
     */
    private $costEH;

    /**
     * @var int
     *
     * @ORM\Column(name="Salary", type="integer", nullable=false)
     */
    private $salary;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getNbWeek(): ?int
    {
        return $this->nbWeek;
    }

    public function setNbWeek(int $nbWeek): self
    {
        $this->nbWeek = $nbWeek;

        return $this;
    }

    public function getCostW(): ?float
    {
        return $this->costW;
    }

    public function setCostW(float $costW): self
    {
        $this->costW = $costW;

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

    public function getSalary(): ?int
    {
        return $this->salary;
    }

    public function setSalary(int $salary): self
    {
        $this->salary = $salary;

        return $this;
    }


}
