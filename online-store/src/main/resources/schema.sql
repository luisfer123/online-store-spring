-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema online-store
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `online-store` ;

-- -----------------------------------------------------
-- Schema online-store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `online-store` DEFAULT CHARACTER SET utf8 ;
USE `online-store` ;

-- -----------------------------------------------------
-- Table `online-store`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Address` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Address` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip_code` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`Users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Users` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Address_id` BIGINT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_User_Address1_idx` (`Address_id` ASC),
  CONSTRAINT `fk_User_Address1`
    FOREIGN KEY (`Address_id`)
    REFERENCES `online-store`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`Products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Products` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  `price` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`Orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Orders` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `User_id` BIGINT NULL,
  `purchase_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Orders_User_idx` (`User_id` ASC),
  CONSTRAINT `fk_Orders_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `online-store`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`Product_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Product_items` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Product_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `Orders_id` BIGINT NULL,
  `Products_id` BIGINT NULL,
  `production_date` DATE NULL,
  `location` VARCHAR(45) NULL,
  `condition` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Product_items_Products1_idx` (`Products_id` ASC),
  INDEX `fk_Product_items_Orders1_idx` (`Orders_id` ASC),
  CONSTRAINT `fk_Product_items_Products1`
    FOREIGN KEY (`Products_id`)
    REFERENCES `online-store`.`Products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Product_items_Orders1`
    FOREIGN KEY (`Orders_id`)
    REFERENCES `online-store`.`Orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`Authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`Authorities` ;

CREATE TABLE IF NOT EXISTS `online-store`.`Authorities` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `online-store`.`User_has_Authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `online-store`.`User_has_Authorities` ;

CREATE TABLE IF NOT EXISTS `online-store`.`User_has_Authorities` (
  `Users_id` BIGINT NOT NULL,
  `Authorities_id` BIGINT NOT NULL,
  PRIMARY KEY (`Users_id`, `Authorities_id`),
  INDEX `fk_User_has_Authorites_Authorites1_idx` (`Authorities_id` ASC),
  INDEX `fk_User_has_Authorites_User1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_User_has_Authorites_User1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `online-store`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Authorites_Authorites1`
    FOREIGN KEY (`Authorities_id`)
    REFERENCES `online-store`.`Authorities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
