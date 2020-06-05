-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema moje_ligi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `moje_ligi` DEFAULT CHARACTER SET utf8 ;
USE `moje_ligi` ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB;

INSERT INTO `users` 
VALUES 
('mati','$2a$10$Gf6ln1gEsljuFOEkGi2/6eFhYmueU5Wp42JCulP8sZv/i4uJv1OUS',1, 'WORKER'),
('marta','$2a$10$LIVPX4xTMEnFNrKZTOWKjuOIlMSBqUXZr6f4YtQWjr2sVMbjZKLfG',1, 'ACCOUNTANT'),
('jakub','$2a$10$NES12gxIslhWmsn9ZYWFxObjdPTL8H0OKnANQDrHhSJlL0lZgTXPq',1, 'WORKER');

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('mati','ROLE_WORKER'),
('marta','ROLE_ACCOUNTANT'),
('jakub','ROLE_WORKER');

-- -----------------------------------------------------
-- Table `moje_ligi`.`zawodnicy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`zawodnicy` (
  `id_zawodnika` INT NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(45) NOT NULL,
  `nazwisko` VARCHAR(45) NOT NULL,
  `pesel` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`id_zawodnika`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`wpisowe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`wpisowe` (
  `id_wpisowe` INT NOT NULL AUTO_INCREMENT,
  `typ` TINYINT NOT NULL,
  `data_uiszczenia` DATETIME NOT NULL,
  `zaplacono` TINYINT NOT NULL,
  `id_zawodnnika` INT NULL,
  PRIMARY KEY (`id_wpisowe`),
  INDEX `fk_wpisowe_zawodnicy1_idx` (`id_zawodnnika` ASC) VISIBLE,
  CONSTRAINT `fk_wpisowe_zawodnicy1`
    FOREIGN KEY (`id_zawodnnika`)
    REFERENCES `moje_ligi`.`zawodnicy` (`id_zawodnika`)
    ON DELETE SET NULL
    ON UPDATE SET NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`obiekty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`obiekty` (
  `id_obiektu` INT NOT NULL AUTO_INCREMENT,
  `koszt` DECIMAL NOT NULL,
  `liczba_godzin` INT NOT NULL,
  `nazwa` VARCHAR(45) NOT NULL,
  `potwierdzono` TINYINT NOT NULL,
  PRIMARY KEY (`id_obiektu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`dyscyplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`dyscyplina` (
  `id_dyscypliny` INT NOT NULL AUTO_INCREMENT,
  `typ` VARCHAR(45) NOT NULL,
  `liczba_rozgrywek` INT NOT NULL,
  `wysokosc_wpisowego` DECIMAL NOT NULL,
  PRIMARY KEY (`id_dyscypliny`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`ligi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`ligi` (
  `id_ligi` INT NOT NULL AUTO_INCREMENT,
  `poziom` VARCHAR(45) NOT NULL,
  `id_obiektu` INT NULL,
  `opis` VARCHAR(100) NOT NULL,
  `id_dyscypliny` INT NULL,
  PRIMARY KEY (`id_ligi`),
  INDEX `fk_ligi_obiekty1_idx` (`id_obiektu` ASC) VISIBLE,
  INDEX `fk_ligi_dyscyplina1_idx` (`id_dyscypliny` ASC) VISIBLE,
  CONSTRAINT `fk_ligi_obiekty1`
    FOREIGN KEY (`id_obiektu`)
    REFERENCES `moje_ligi`.`obiekty` (`id_obiektu`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ligi_dyscyplina1`
    FOREIGN KEY (`id_dyscypliny`)
    REFERENCES `moje_ligi`.`dyscyplina` (`id_dyscypliny`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`sezony`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`sezony` (
  `id_sezonu` INT NOT NULL AUTO_INCREMENT,
  `numer` INT NOT NULL,
  `opis` VARCHAR(200) NOT NULL,
  `id_ligi` INT NOT NULL,
  PRIMARY KEY (`id_sezonu`),
  INDEX `fk_sezony_ligi1_idx` (`id_ligi` ASC) VISIBLE,
  CONSTRAINT `fk_sezony_ligi1`
    FOREIGN KEY (`id_ligi`)
    REFERENCES `moje_ligi`.`ligi` (`id_ligi`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`kolejki`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`kolejki` (
  `id_kolejki` INT NOT NULL AUTO_INCREMENT,
  `dyscyplina` VARCHAR(45) NOT NULL,
  `numer` INT NOT NULL,
  `id_sezonu` INT NOT NULL,
  PRIMARY KEY (`id_kolejki`),
  INDEX `fk_kolejki_sezony1_idx` (`id_sezonu` ASC) VISIBLE,
  CONSTRAINT `fk_kolejki_sezony1`
    FOREIGN KEY (`id_sezonu`)
    REFERENCES `moje_ligi`.`sezony` (`id_sezonu`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`mecze`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`mecze` (
  `id_meczu` INT NOT NULL AUTO_INCREMENT,
  `termin` DATETIME NOT NULL,
  `miejsce` VARCHAR(45) NOT NULL,
  `wynik_pierwszego` INT NULL,
  `wynik_drugiego` INT NULL,
  `id_pierwszego_zawodnika` INT NULL,
  `id_drugiego_zawodnika` INT NULL,
  `id_kolejki` INT NOT NULL,
  PRIMARY KEY (`id_meczu`),
  INDEX `fk_mecze_kolejki1_idx` (`id_kolejki` ASC) VISIBLE,
  CONSTRAINT `fk_mecze_kolejki1`
    FOREIGN KEY (`id_kolejki`)
    REFERENCES `moje_ligi`.`kolejki` (`id_kolejki`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`sety`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`sety` (
  `id_setu` INT NOT NULL,
  `numer` INT NOT NULL,
  `punkty_pierwszego` INT NULL,
  `punkty_drugiego` VARCHAR(45) NULL,
  `id_meczu` INT NOT NULL,
  PRIMARY KEY (`id_setu`),
  INDEX `fk_sety_mecze1_idx` (`id_meczu` ASC) VISIBLE,
  CONSTRAINT `fk_sety_mecze1`
    FOREIGN KEY (`id_meczu`)
    REFERENCES `moje_ligi`.`mecze` (`id_meczu`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`wpisowe_kolejki`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`wpisowe_kolejki` (
  `id_wpisowe` INT NOT NULL,
  `id_kolejki` INT NOT NULL,
  PRIMARY KEY (`id_wpisowe`, `id_kolejki`),
  INDEX `fk_wpisowe_has_kolejki_kolejki1_idx` (`id_kolejki` ASC) VISIBLE,
  INDEX `fk_wpisowe_has_kolejki_wpisowe1_idx` (`id_wpisowe` ASC) VISIBLE,
  CONSTRAINT `fk_wpisowe_has_kolejki_wpisowe1`
    FOREIGN KEY (`id_wpisowe`)
    REFERENCES `moje_ligi`.`wpisowe` (`id_wpisowe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wpisowe_has_kolejki_kolejki1`
    FOREIGN KEY (`id_kolejki`)
    REFERENCES `moje_ligi`.`kolejki` (`id_kolejki`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`wpisowe_ligi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`wpisowe_ligi` (
  `id_wpisowe` INT NOT NULL,
  `id_ligi` INT NOT NULL,
  PRIMARY KEY (`id_wpisowe`, `id_ligi`),
  INDEX `fk_wpisowe_has_ligi_ligi1_idx` (`id_ligi` ASC) VISIBLE,
  INDEX `fk_wpisowe_has_ligi_wpisowe1_idx` (`id_wpisowe` ASC) VISIBLE,
  CONSTRAINT `fk_wpisowe_has_ligi_wpisowe1`
    FOREIGN KEY (`id_wpisowe`)
    REFERENCES `moje_ligi`.`wpisowe` (`id_wpisowe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wpisowe_has_ligi_ligi1`
    FOREIGN KEY (`id_ligi`)
    REFERENCES `moje_ligi`.`ligi` (`id_ligi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`zawodnicy_ligi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`zawodnicy_ligi` (
  `id_zawodnika` INT NOT NULL,
  `id_ligi` INT NOT NULL,
  PRIMARY KEY (`id_zawodnika`, `id_ligi`),
  INDEX `fk_zawodnicy_has_ligi_ligi1_idx` (`id_ligi` ASC) VISIBLE,
  INDEX `fk_zawodnicy_has_ligi_zawodnicy1_idx` (`id_zawodnika` ASC) VISIBLE,
  CONSTRAINT `fk_zawodnicy_has_ligi_zawodnicy1`
    FOREIGN KEY (`id_zawodnika`)
    REFERENCES `moje_ligi`.`zawodnicy` (`id_zawodnika`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_zawodnicy_has_ligi_ligi1`
    FOREIGN KEY (`id_ligi`)
    REFERENCES `moje_ligi`.`ligi` (`id_ligi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moje_ligi`.`sezony_wpisowe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moje_ligi`.`sezony_wpisowe` (
  `id_sezonu` INT NOT NULL,
  `id_wpisowe` INT NOT NULL,
  PRIMARY KEY (`id_sezonu`, `id_wpisowe`),
  INDEX `fk_sezony_has_wpisowe_wpisowe1_idx` (`id_wpisowe` ASC) VISIBLE,
  INDEX `fk_sezony_has_wpisowe_sezony1_idx` (`id_sezonu` ASC) VISIBLE,
  CONSTRAINT `fk_sezony_has_wpisowe_sezony1`
    FOREIGN KEY (`id_sezonu`)
    REFERENCES `moje_ligi`.`sezony` (`id_sezonu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sezony_has_wpisowe_wpisowe1`
    FOREIGN KEY (`id_wpisowe`)
    REFERENCES `moje_ligi`.`wpisowe` (`id_wpisowe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
