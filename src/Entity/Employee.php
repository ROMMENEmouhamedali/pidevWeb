<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Employee
 *
 * @ORM\Table(name="employee")
 * @ORM\Entity
 */
class Employee
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
     * @var string
     *
     * @ORM\Column(name="GENDER", type="string", length=50, nullable=false)
     */
    private $gender;

    /**
     * @var int
     *
     * @ORM\Column(name="ID_SALARY", type="integer", nullable=false)
     */
    private $idSalary;

    public function getIdEmployee(): ?int
    {
        return $this->idEmployee;
    }

    public function getGender(): ?string
    {
        return $this->gender;
    }

    public function setGender(string $gender): self
    {
        $this->gender = $gender;

        return $this;
    }

    public function getIdSalary(): ?int
    {
        return $this->idSalary;
    }

    public function setIdSalary(int $idSalary): self
    {
        $this->idSalary = $idSalary;

        return $this;
    }


}
