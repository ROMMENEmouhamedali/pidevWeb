<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\PlatterRepository;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
/**
 * Platter
 *
 * @ORM\Table(name="platter")
 * @ORM\Entity(repositoryClass=PlatterRepository::class)
  * @UniqueEntity(
 *     fields={"nameplatter"},
 *     message="Platter Name already exists"
 * )
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
    * @Assert\Length(
     *      min = 2,
     *      max = 15,
     *      minMessage = "Platter Name must be at least {{ limit }} characters long",
     *      maxMessage = "Platter Name cannot be longer than {{ limit }} characters"
     * )
     * @ORM\Column(name="NAMEPLATTER", type="string", length=50, nullable=false,unique=true)
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
     * @Assert\Type( type="integer")
     * @ORM\Column(name="PRICEPLATTER", type="integer", nullable=false)
     * @Assert\Range(min = 25,max = 150,
     *      notInRangeMessage = "You must enter a price between {{ min }}D and {{ max }}D ",
     * )
     */
    private $priceplatter;

    /**
     * @var int
    * @Assert\Type(
     *     type="integer",
     *     message="The value {{ value }} is not a valid {{ type }}.")
     * @ORM\Column(name="NBPLATTER", type="integer", nullable=false)
     */
    private $nbplatter;

    /**
     * @var string
     * @Assert\Length(
     *      min = 2,
     *      max = 10,
     *      minMessage = "Platter description must be at least {{ limit }} characters long",
     *      maxMessage = "Platter description cannot be longer than {{ limit }} characters"
     * )
     * @ORM\Column(name="DESCRIPTIONPLATTER", type="string", length=50, nullable=false)
     */
    private $descriptionplatter;

    /**
     * @var string
     * @ORM\Column(name="TypePlatter", type="string", length=255, nullable=false)
     */
    private $typeplatter;

    /**
     * @var string|null
     *
     * @ORM\Column(name="ImagePlatter", type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="Please upload image")
     * @Assert\Image(
     *     minWidth = 200,
     *     maxWidth = 4000,
     *     minHeight = 200,
     *     maxHeight = 4000
     * )
     * @Assert\File(mimeTypes={"image/jpeg"}) 
     */
    private $imageplatter;

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

    public function getImageplatter() 
    {
        return $this->imageplatter;
    }

    public function setImageplatter( $imageplatter)
    {
        $this->imageplatter = $imageplatter;

        return $this;
    }


}
