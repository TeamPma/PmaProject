-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`VeterinaryClinic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`VeterinaryClinic` ;

CREATE TABLE IF NOT EXISTS `mydb`.`VeterinaryClinic` (
  `idVeterinaryClinic` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(128) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idVeterinaryClinic`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Shelter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Shelter` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Shelter` (
  `idShelter` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(128) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `bankAccount` INT NULL,
  PRIMARY KEY (`idShelter`),
  UNIQUE INDEX `idShelter_UNIQUE` (`idShelter` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Dog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Dog` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Dog` (
  `idShelter` INT NOT NULL,
  `dogId` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `bread` VARCHAR(45) NULL,
  `gender` INT NOT NULL,
  `weight` DECIMAL NULL,
  `height` DECIMAL NULL,
  `age` INT NULL,
  `sterilizied` TINYINT(1) NOT NULL,
  `marked` TINYINT(1) NOT NULL,
  `anamnesis` VARCHAR(128) NULL,
  PRIMARY KEY (`idShelter`, `dogId`),
  CONSTRAINT `fk_Dog_Shelter`
    FOREIGN KEY (`idShelter`)
    REFERENCES `mydb`.`Shelter` (`idShelter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Announcement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Announcement` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Announcement` (
  `idShelter` INT NOT NULL,
  `idAnnouncement` INT NOT NULL,
  `comment` VARCHAR(512) NOT NULL,
  `url` VARCHAR(128) NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`idShelter`, `idAnnouncement`),
  CONSTRAINT `fk_Announcement_Shelter1`
    FOREIGN KEY (`idShelter`)
    REFERENCES `mydb`.`Shelter` (`idShelter`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
