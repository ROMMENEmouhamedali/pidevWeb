<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Room
 *
 * @ORM\Table(name="room")
 * @ORM\Entity
 */
class Room
{
    /**
     * @var int
     *
     * @ORM\Column(name="roomId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $roomid;

    /**
     * @var int
     *
     * @ORM\Column(name="roomNumber", type="integer", nullable=false)
     */
    private $roomnumber;

    /**
     * @var string
     *
     * @ORM\Column(name="roomType", type="string", length=255, nullable=false)
     */
    private $roomtype;

    /**
     * @var float
     *
     * @ORM\Column(name="roomPricePerNight", type="float", precision=10, scale=0, nullable=false)
     */
    private $roompricepernight;

    /**
     * @var string
     *
     * @ORM\Column(name="roomDirection", type="string", length=255, nullable=false)
     */
    private $roomdirection;

    public function getRoomid(): ?int
    {
        return $this->roomid;
    }

    public function getRoomnumber(): ?int
    {
        return $this->roomnumber;
    }

    public function setRoomnumber(int $roomnumber): self
    {
        $this->roomnumber = $roomnumber;

        return $this;
    }

    public function getRoomtype(): ?string
    {
        return $this->roomtype;
    }

    public function setRoomtype(string $roomtype): self
    {
        $this->roomtype = $roomtype;

        return $this;
    }

    public function getRoompricepernight(): ?float
    {
        return $this->roompricepernight;
    }

    public function setRoompricepernight(float $roompricepernight): self
    {
        $this->roompricepernight = $roompricepernight;

        return $this;
    }

    public function getRoomdirection(): ?string
    {
        return $this->roomdirection;
    }

    public function setRoomdirection(string $roomdirection): self
    {
        $this->roomdirection = $roomdirection;

        return $this;
    }


}
