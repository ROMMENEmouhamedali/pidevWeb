<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220507120710 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE calenderroom (calenderRoomId INT AUTO_INCREMENT NOT NULL, dateRoom DATE NOT NULL, state INT NOT NULL, fkCalenderRoom INT DEFAULT NULL, INDEX fkCalenderRoom (fkCalenderRoom), PRIMARY KEY(calenderRoomId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE progressroom (idProgress INT AUTO_INCREMENT NOT NULL, progress DOUBLE PRECISION NOT NULL, PRIMARY KEY(idProgress)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (fk_user_reservation INT DEFAULT NULL, fk_room_reservation INT DEFAULT NULL, reservationId INT AUTO_INCREMENT NOT NULL, arrivalDate DATE NOT NULL, departureDate DATE NOT NULL, specializedRequest VARCHAR(255) NOT NULL, INDEX fk_room_reservation (fk_room_reservation), INDEX fk_user_reservation (fk_user_reservation), PRIMARY KEY(reservationId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE room (roomId INT AUTO_INCREMENT NOT NULL, roomNumber INT NOT NULL, roomType VARCHAR(255) NOT NULL, roomPricePerNight DOUBLE PRECISION NOT NULL, roomDirection VARCHAR(255) NOT NULL, PRIMARY KEY(roomId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE calenderroom ADD CONSTRAINT FK_5A0468C4D54C02C8 FOREIGN KEY (fkCalenderRoom) REFERENCES room (roomId)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955BDB9E7B7 FOREIGN KEY (fk_user_reservation) REFERENCES user (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495597801D FOREIGN KEY (fk_room_reservation) REFERENCES room (roomId)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE calenderroom DROP FOREIGN KEY FK_5A0468C4D54C02C8');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495597801D');
        $this->addSql('DROP TABLE calenderroom');
        $this->addSql('DROP TABLE progressroom');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE room');
    }
}
