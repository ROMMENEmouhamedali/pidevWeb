<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220506234706 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE technicalsheettt');
        $this->addSql('ALTER TABLE platter DROP Tomato, DROP potato, DROP pepper, DROP onion');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE technicalsheettt (fkProduct INT NOT NULL, fkPlatter INT DEFAULT NULL, Qte INT NOT NULL, diffPrice DOUBLE PRECISION NOT NULL, INDEX fk_platter_TS (fkPlatter), INDEX fk_product_Ts (fkProduct), PRIMARY KEY(fkProduct)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE technicalsheettt ADD CONSTRAINT FK_5A1157FC56AB222 FOREIGN KEY (fkProduct) REFERENCES product (IdProduct)');
        $this->addSql('ALTER TABLE technicalsheettt ADD CONSTRAINT FK_5A1157FAD6450B3 FOREIGN KEY (fkPlatter) REFERENCES platter (IdPlatter)');
        $this->addSql('ALTER TABLE platter ADD Tomato TINYINT(1) NOT NULL, ADD potato TINYINT(1) NOT NULL, ADD pepper TINYINT(1) NOT NULL, ADD onion TINYINT(1) NOT NULL');
    }
}
