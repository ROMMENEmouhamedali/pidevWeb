<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Product
 *
 * @ORM\Table(name="product")
 * @ORM\Entity
 */
class Product
{
    /**
     * @var int
     *
     * @ORM\Column(name="IdProduct", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idproduct;

    /**
     * @var string
     *
     * @ORM\Column(name="RefProduct", type="string", length=255, nullable=false)
     */
    private $refproduct;

    /**
     * @var string
     *
     * @ORM\Column(name="SupplierName", type="string", length=255, nullable=false)
     */
    private $suppliername;

    /**
     * @var float
     *
     * @ORM\Column(name="UnitPriceProduct", type="float", precision=10, scale=0, nullable=false)
     */
    private $unitpriceproduct;

    /**
     * @var int
     *
     * @ORM\Column(name="QuantityProduct", type="integer", nullable=false)
     */
    private $quantityproduct;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Platter", inversedBy="fkproduct")
     * @ORM\JoinTable(name="technicalsheet",
     *   joinColumns={
     *     @ORM\JoinColumn(name="fkProduct", referencedColumnName="IdProduct")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="fkPlatter", referencedColumnName="IdPlatter")
     *   }
     * )
     */
    private $fkplatter;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->fkplatter = new \Doctrine\Common\Collections\ArrayCollection();
    }

    public function getIdproduct(): ?int
    {
        return $this->idproduct;
    }

    public function getRefproduct(): ?string
    {
        return $this->refproduct;
    }

    public function setRefproduct(string $refproduct): self
    {
        $this->refproduct = $refproduct;

        return $this;
    }

    public function getSuppliername(): ?string
    {
        return $this->suppliername;
    }

    public function setSuppliername(string $suppliername): self
    {
        $this->suppliername = $suppliername;

        return $this;
    }

    public function getUnitpriceproduct(): ?float
    {
        return $this->unitpriceproduct;
    }

    public function setUnitpriceproduct(float $unitpriceproduct): self
    {
        $this->unitpriceproduct = $unitpriceproduct;

        return $this;
    }

    public function getQuantityproduct(): ?int
    {
        return $this->quantityproduct;
    }

    public function setQuantityproduct(int $quantityproduct): self
    {
        $this->quantityproduct = $quantityproduct;

        return $this;
    }

    /**
     * @return Collection<int, Platter>
     */
    public function getFkplatter(): Collection
    {
        return $this->fkplatter;
    }

    public function addFkplatter(Platter $fkplatter): self
    {
        if (!$this->fkplatter->contains($fkplatter)) {
            $this->fkplatter[] = $fkplatter;
        }

        return $this;
    }

    public function removeFkplatter(Platter $fkplatter): self
    {
        $this->fkplatter->removeElement($fkplatter);

        return $this;
    }

}
