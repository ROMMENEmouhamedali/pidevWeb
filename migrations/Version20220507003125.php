<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220507003125 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE platter ADD fk_ingredient INT DEFAULT NULL, DROP ingredient');
        $this->addSql('ALTER TABLE platter ADD CONSTRAINT FK_BB44E63C9BC1B2E3 FOREIGN KEY (fk_ingredient) REFERENCES product (IdProduct)');
        $this->addSql('CREATE INDEX IDX_BB44E63C9BC1B2E3 ON platter (fk_ingredient)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE platter DROP FOREIGN KEY FK_BB44E63C9BC1B2E3');
        $this->addSql('DROP INDEX IDX_BB44E63C9BC1B2E3 ON platter');
        $this->addSql('ALTER TABLE platter ADD ingredient VARCHAR(50) NOT NULL, DROP fk_ingredient');
    }
}
