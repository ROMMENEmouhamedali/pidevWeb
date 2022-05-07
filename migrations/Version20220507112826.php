<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220507112826 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE platter (fk_ingredient INT DEFAULT NULL, IdPlatter INT AUTO_INCREMENT NOT NULL, NAMEPLATTER VARCHAR(50) NOT NULL, PRICEPLATTER INT NOT NULL, NBPLATTER INT NOT NULL, DESCRIPTIONPLATTER VARCHAR(50) NOT NULL, TypePlatter VARCHAR(255) NOT NULL, ImagePlatter VARCHAR(255) DEFAULT NULL, UNIQUE INDEX UNIQ_BB44E63CC90C001A (NAMEPLATTER), INDEX IDX_BB44E63C9BC1B2E3 (fk_ingredient), PRIMARY KEY(IdPlatter)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE product (IdProduct INT AUTO_INCREMENT NOT NULL, RefProduct VARCHAR(255) NOT NULL, SupplierName VARCHAR(255) NOT NULL, UnitPriceProduct DOUBLE PRECISION NOT NULL, QuantityProduct INT NOT NULL, PRIMARY KEY(IdProduct)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE platter ADD CONSTRAINT FK_BB44E63C9BC1B2E3 FOREIGN KEY (fk_ingredient) REFERENCES product (IdProduct)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE platter DROP FOREIGN KEY FK_BB44E63C9BC1B2E3');
        $this->addSql('DROP TABLE platter');
        $this->addSql('DROP TABLE product');
    }
}
