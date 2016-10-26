-- MySQL Workbench Synchronization
-- Generated: 2016-10-26 13:51
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Adriano

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_carrinho_de_compras` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `db_carrinho_de_compras`.`produto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valorUnitario` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_carrinho_de_compras`.`carrinho` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_produto` INT(11) NOT NULL,
  `qtd_produto` INT(11) NOT NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `db_carrinho_de_compras`.`pedido` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

ALTER TABLE `db_carrinho_de_compras`.`carrinho` 
ADD INDEX `fk_carrinho_produto_idx` (`id_produto` ASC)
ALTER TABLE `db_carrinho_de_compras`.`carrinho` 
ADD CONSTRAINT `fk_carrinho_produto`
  FOREIGN KEY (`id_produto`)
  REFERENCES `db_carrinho_de_compras`.`produto` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `db_carrinho_de_compras`.`produto` (`id`, `nome`, `valorUnitario`) VALUES ('1', 'Computador', '1200.0');
INSERT INTO `db_carrinho_de_compras`.`produto` (`id`, `nome`, `valorUnitario`) VALUES ('2', 'Televisão', '900.0');
INSERT INTO `db_carrinho_de_compras`.`produto` (`id`, `nome`, `valorUnitario`) VALUES ('3', 'Fogão', '550.0');
INSERT INTO `db_carrinho_de_compras`.`produto` (`id`, `nome`, `valorUnitario`) VALUES ('4', 'Geladeira', '1400.0');
INSERT INTO `db_carrinho_de_compras`.`produto` (`id`, `nome`, `valorUnitario`) VALUES ('5', 'Guarda-roupas', '850.0');

