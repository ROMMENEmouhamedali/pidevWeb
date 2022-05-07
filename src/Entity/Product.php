<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\ProductRepository;
/**
 * Product
 *
 * @ORM\Table(name="product")
 * @ORM\Entity(repositoryClass=ProductRepository::class)
 * 
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
      * @Assert\Length(
     *      min = 2,
     *      max = 6,
     *      minMessage = "Supplier Name must be at least {{ limit }} characters long",
     *      maxMessage = "Supplier Name cannot be longer than {{ limit }} characters"
     * )
     * @ORM\Column(name="RefProduct", type="string", length=255, nullable=false)
     */
    private $refproduct;

    /**
     * @var string
     * @Assert\Length(
     *      min = 2,
     *      max = 6,
     *      minMessage = "Supplier Name must be at least {{ limit }} characters long",
     *      maxMessage = "Supplier Name cannot be longer than {{ limit }} characters"
     * )
     * @ORM\Column(name="SupplierName", type="string", length=255, nullable=false)
     */
    private $suppliername;

    /**
     * @var float
     * @Assert\Type( type="float")
     * @ORM\Column(name="UnitPriceProduct", type="float", precision=10, scale=0, nullable=false)
     */
    private $unitpriceproduct;

    /**
     * @var int
     * @Assert\Type(
     *     type="integer",
     *     message="The value {{ value }} is not a valid {{ type }}.")
     * @ORM\Column(name="QuantityProduct", type="integer", nullable=false)
     */
    private $quantityproduct;

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


}
