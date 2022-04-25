<?php

namespace App\Entity;

class PropertyEventSearch
{

    private $nameevent;


    public function getNameevent(): ?string
    {
        return $this->nameevent;
    }

    public function setNameevent(string $nameevent): self
    {
        $this->nameevent = $nameevent;

        return $this;
    }
}