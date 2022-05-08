<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Platter
 *
 * @ORM\Table(name="platter")
 * @ORM\Entity
 */
class Platter
{
    /**
     * @var int
     *
     * @ORM\Column(name="IdPlatter", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idplatter;

    /**
     * @var string
     *
     * @ORM\Column(name="NAMEPLATTER", type="string", length=50, nullable=false)
     */
    private $nameplatter;

    /**
     * @var string
     *
     * @ORM\Column(name="ingredient", type="string", length=50, nullable=false)
     */
    private $ingredient;

    /**
     * @var int
     *
     * @ORM\Column(name="PRICEPLATTER", type="integer", nullable=false)
     */
    private $priceplatter;

    /**
     * @var int
     *
     * @ORM\Column(name="NBPLATTER", type="integer", nullable=false)
     */
    private $nbplatter;

    /**
     * @var string
     *
     * @ORM\Column(name="DESCRIPTIONPLATTER", type="string", length=50, nullable=false)
     */
    private $descriptionplatter;

    /**
     * @var string
     *
     * @ORM\Column(name="TypePlatter", type="string", length=255, nullable=false)
     */
    private $typeplatter;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Product", mappedBy="fkplatter")
     */
    private $fkproduct;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkproduct = new \Doctrine\Common\Collections\ArrayCollection();
    }

    public function getIdplatter(): ?int
    {
        return $this->idplatter;
    }

    public function getNameplatter(): ?string
    {
        return $this->nameplatter;
    }

    public function setNameplatter(string $nameplatter): self
    {
        $this->nameplatter = $nameplatter;

        return $this;
    }

    public function getIngredient(): ?string
    {
        return $this->ingredient;
    }

    public function setIngredient(string $ingredient): self
    {
        $this->ingredient = $ingredient;

        return $this;
    }

    public function getPriceplatter(): ?int
    {
        return $this->priceplatter;
    }

    public function setPriceplatter(int $priceplatter): self
    {
        $this->priceplatter = $priceplatter;

        return $this;
    }

    public function getNbplatter(): ?int
    {
        return $this->nbplatter;
    }

    public function setNbplatter(int $nbplatter): self
    {
        $this->nbplatter = $nbplatter;

        return $this;
    }

    public function getDescriptionplatter(): ?string
    {
        return $this->descriptionplatter;
    }

    public function setDescriptionplatter(string $descriptionplatter): self
    {
        $this->descriptionplatter = $descriptionplatter;

        return $this;
    }

    public function getTypeplatter(): ?string
    {
        return $this->typeplatter;
    }

    public function setTypeplatter(string $typeplatter): self
    {
        $this->typeplatter = $typeplatter;

        return $this;
    }

    /**
     * @return Collection<int, Product>
     */
    public function getFkproduct(): Collection
    {
        return $this->fkproduct;
    }

    public function addFkproduct(Product $fkproduct): self
    {
        if (!$this->fkproduct->contains($fkproduct)) {
            $this->fkproduct[] = $fkproduct;
            $fkproduct->addFkplatter($this);
        }

        return $this;
    }

    public function removeFkproduct(Product $fkproduct): self
    {
        if ($this->fkproduct->removeElement($fkproduct)) {
            $fkproduct->removeFkplatter($this);
        }

        return $this;
    }

}
