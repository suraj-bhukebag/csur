-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Train`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Train` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trainnumber` VARCHAR(45) NOT NULL,
  `startingstation` VARCHAR(45) NOT NULL,
  `endingstation` VARCHAR(45) NOT NULL,
  `capacity` INT NOT NULL DEFAULT 1000,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RunningTrains`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RunningTrains` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trainid` INT NOT NULL,
  `date` BIGINT(13) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `availablecount` INT NOT NULL,
  `ticketsBooked` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `trainno_idx` (`trainid` ASC),
  CONSTRAINT `trainno`
    FOREIGN KEY (`trainid`)
    REFERENCES `mydb`.`Train` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `contactnumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `username`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numberofpassengers` INT NOT NULL,
  `source` VARCHAR(45) NOT NULL,
  `destination` VARCHAR(45) NOT NULL,
  `totalPrice` DECIMAL NOT NULL,
  `bookedby` INT NOT NULL,
  `travellingDate` BIGINT(13) NOT NULL,
  `tripType` VARCHAR(45) NOT NULL,
  `numberOfConnections` INT NULL,
  `bookingDate` BIGINT(13) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `bookinguser_idx` (`bookedby` ASC),
  CONSTRAINT `bookinguser`
    FOREIGN KEY (`bookedby`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Station` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TrainSchedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TrainSchedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trainId` INT NOT NULL,
  `stationId` INT NOT NULL,
  `arrivalTime` VARCHAR(45) NOT NULL,
  `departureTime` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `stationRef_idx` (`stationId` ASC),
  INDEX `trainRef_idx` (`trainId` ASC),
  CONSTRAINT `stationRef`
    FOREIGN KEY (`stationId`)
    REFERENCES `mydb`.`Station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `trainRef`
    FOREIGN KEY (`trainId`)
    REFERENCES `mydb`.`Train` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Travellers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Travellers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `bookingId` INT NOT NULL,
  `age` INT NULL,
  `gender` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  INDEX `bokingid_idx` (`bookingId` ASC),
  CONSTRAINT `bokingid`
    FOREIGN KEY (`bookingId`)
    REFERENCES `mydb`.`Ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TicketPrice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TicketPrice` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `express` DECIMAL(2,2) NULL,
  `regular` DECIMAL(2,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TicketDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TicketDetails` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ticketId` INT NOT NULL,
  `trainId` INT NOT NULL,
  `from` VARCHAR(45) NULL,
  `to` VARCHAR(45) NULL,
  `arrivalTime` BIGINT(13) NULL,
  `departureTime` BIGINT(13) NULL,
  `price` DECIMAL(2,2) NULL,
  `sequenceNumber` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `ticketId_idx` (`ticketId` ASC),
  INDEX `trainid_idx` (`trainId` ASC),
  CONSTRAINT `ticketId`
    FOREIGN KEY (`ticketId`)
    REFERENCES `mydb`.`Ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `trainid`
    FOREIGN KEY (`trainId`)
    REFERENCES `mydb`.`Train` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
