<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20231108000840 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE commentaire CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE iduser iduser INT NOT NULL, CHANGE idformation idformation INT NOT NULL, CHANGE date date DATETIME NOT NULL');
        $this->addSql('ALTER TABLE formation CHANGE id id INT AUTO_INCREMENT NOT NULL, CHANGE titre titre VARCHAR(150) NOT NULL, CHANGE categories categories VARCHAR(150) NOT NULL, CHANGE duree duree VARCHAR(100) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE messenger_messages');
        $this->addSql('ALTER TABLE commentaire CHANGE id id BIGINT AUTO_INCREMENT NOT NULL, CHANGE iduser iduser BIGINT NOT NULL, CHANGE idformation idformation BIGINT NOT NULL, CHANGE date date DATE NOT NULL');
        $this->addSql('ALTER TABLE formation CHANGE id id BIGINT AUTO_INCREMENT NOT NULL, CHANGE titre titre VARCHAR(50) NOT NULL, CHANGE categories categories VARCHAR(50) NOT NULL, CHANGE duree duree VARCHAR(30) NOT NULL');
    }
}
