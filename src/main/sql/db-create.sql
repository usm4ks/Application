DROP SCHEMA IF EXISTS mydb;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `author` VARCHAR(50) NOT NULL,
  `publishing_house` VARCHAR(50) NOT NULL,
  `year` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(24) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `first_name` VARCHAR(24) NOT NULL,
  `last_name` VARCHAR(24) NOT NULL,
  `role` ENUM('admin', 'librarian', 'user') NOT NULL DEFAULT 'user',
  `blocked` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `mydb`.`books_in_hall` (
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `book_id`),
  INDEX `fk_books_has_users_users2_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_books_has_users_books1_idx` (`book_id` ASC) VISIBLE,
  CONSTRAINT `fk_books_has_users_books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_books_has_users_users2`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`orders` (
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `type` ENUM('on_ticket', 'in_hall') NOT NULL,
  PRIMARY KEY (`user_id`, `book_id`),
  INDEX `fk_users_has_books_books1_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_users_has_books_users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_books_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_books_books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`books_on_ticket` (
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL DEFAULT 0,
  `until_date` DATE NOT NULL,
  `fine` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `book_id`),
  INDEX `fk_users_has_books_books2_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_users_has_books_users2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_books_users2`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_books_books2`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Harry Potter and the Philosophers Stone', 'J. K. Rowling', 'Scholastic', '1997', '3');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('The Hobbit', 'J. R. R. Tolkien', 'George Allen & Unwin', '1937', '2');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('The Little Prince', 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', '1943', '1');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Rich Dad, Poor Dad', 'Robert Kiyosaki', 'Warner Books Ed', '2000', '5');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('The Da Vinci Code', 'Dan Brown', 'Doubleday', '2003', '2');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('The Alchemist', 'Paulo Coelho', 'HarperTorch', '1988', '1');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'Charles Scribner''s Sons', '1925', '1');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Собачье сердце', 'М. А. Булгаков.', 'Студент', '1987', '2');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Никогда не сдавайтесь', 'Уинстон Черчилль', 'Эксмо', '2010', '3');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Бедные люди', 'Ф. М. Достоевский', 'Пушкин Дом', '1999', '3');
INSERT INTO books(title, author, publishing_house, year, amount)
VALUES ('Капитанская дочка', 'А. С. Пушкин', 'Издатель', '2001', '4');
INSERT INTO users(email, password, first_name, last_name)
VALUES ('ivanov@gmail.com', 'ce24659f7118b6d307b3a243dba8214e', 'Ivan', 'Ivanov');
INSERT INTO users(email, password, first_name, last_name)
VALUES ('petrov@gmail.com', 'f75c6c53975c41f5c3875115e2fa7585', 'Petr', 'Petrov');
INSERT INTO users(email, password, first_name, last_name)
VALUES ('sidorov@gmail.com', '0d168244a5bc98d085868a21bc33e9f3', 'Sidor', 'Sidorov');
INSERT INTO users(email, password, first_name, last_name, role)
VALUES ('semenov@gmail.com', 'e0e13965e1a36945c02a57f9950cf21b', 'Semen', 'Semenov', 'librarian');
INSERT INTO users(email, password, first_name, last_name, role)
VALUES ('filipov@gmail.com', 'dddf2aa7de019716976a0b76c83fbf67', 'Filip', 'Filipov', 'admin');
INSERT INTO users(email, password, first_name, last_name)
VALUES ('sidorenko@gmail.com', '6023a539290e1811d63a93bfab6ffa90', 'Сидоренко', 'Маргарига');
