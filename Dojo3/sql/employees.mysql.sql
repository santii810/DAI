CREATE DATABASE  IF NOT EXISTS `employees`;
USE `employees`;

DROP TABLE IF EXISTS Employees;

CREATE  TABLE `employees`.`Employees` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL ,
  `surname` VARCHAR(100) NULL ,
  `birth` DATE NULL ,
  `salary` INT NULL ,
  `intern` BIT NULL ,
  PRIMARY KEY (`id`)
);
