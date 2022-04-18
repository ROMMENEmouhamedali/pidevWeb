<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Technicalsheet
 *
 * @ORM\Table(name="technicalsheet", indexes={@ORM\Index(name="fk_product_Ts", columns={"fkProduct"}), @ORM\Index(name="fk_platter_TS", columns={"fkPlatter"})})
 * @ORM\Entity
 */
class Technicalsheet
{
    /**
     * @var int
     *
     * @ORM\Column(name="Qte", type="integer", nullable=false)
     */
    private $qte;

    /**
     * @var float
     *
     * @ORM\Column(name="diffPrice", type="float", precision=10, scale=0, nullable=false)
     */
    private $diffprice;

    /**
     * @var \Product
     *
     * @ORM\ManyToOne(targetEntity="Product")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fkProduct", referencedColumnName="IdProduct")
     * })
     */
    private $fkproduct;

    /**
     * @var \Platter
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Platter")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="fkPlatter", referencedColumnName="IdPlatter")
     * })
     */
    private $fkplatter;

    public function getQte(): ?int
    {
        return $this->qte;
    }

    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }

    public function getDiffprice(): ?float
    {
        return $this->diffprice;
    }

    public function setDiffprice(float $diffprice): self
    {
        $this->diffprice = $diffprice;

        return $this;
    }

    public function getFkproduct(): ?Product
    {
        return $this->fkproduct;
    }

    public function setFkproduct(?Product $fkproduct): self
    {
        $this->fkproduct = $fkproduct;

        return $this;
    }

    public function getFkplatter(): ?Platter
    {
        return $this->fkplatter;
    }

    public function setFkplatter(?Platter $fkplatter): self
    {
        $this->fkplatter = $fkplatter;

        return $this;
    }


}
