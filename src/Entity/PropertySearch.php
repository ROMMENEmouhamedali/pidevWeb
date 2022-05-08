<?php
namespace App\Entity;

class PropertySearch
{

   private $number;

   public function getNumber(): ?String
   {
            return $this->number;
   }
  

   public function setNumber(String $number): self
   {
      $this->number=$number;
      return  $this;

   }
   


















}