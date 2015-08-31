CREATE DATABASE IF NOT EXISTS budget_rush;
USE budget_rush;

CREATE TABLE users (
  id   INT(5) AUTO_INCREMENT NOT NULL,
  name VARCHAR(50)           NOT NULL,
  pass VARCHAR(20),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE currencies (
  id         INT(5) AUTO_INCREMENT NOT NULL,
  name       VARCHAR(15)           NOT NULL,
  short_name VARCHAR(3)            NOT NULL,
  code       INT(4)                NOT NULL,
  symbol     VARCHAR(1)            NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE accounts (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(50)        NOT NULL,
  user_id     INT(5)             NOT NULL,
  currency_id INT(5)             NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (currency_id) REFERENCES currencies (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;


CREATE TABLE categories (
  id     INT AUTO_INCREMENT NOT NULL,
  name   VARCHAR(50)        NOT NULL,
  parent INT(5),
  PRIMARY KEY (id),
  FOREIGN KEY (parent) REFERENCES categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE TABLE contractors (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(50)        NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE orders (
  id          INT AUTO_INCREMENT NOT NULL,
  amount      DOUBLE(10, 2)      NOT NULL,
  date        TIMESTAMP          NOT NULL,
  type        VARCHAR(10)        NOT NULL,
  accaunt_id  INT(5)             NOT NULL,
  category_id INT(5)             NOT NULL,
  contractor_id   INT(5),
  expense_id  INT(5),
  income_id   INT(5),
  PRIMARY KEY (id),
  FOREIGN KEY (accaunt_id) REFERENCES accounts (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (category_id) REFERENCES categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (contractor_id) REFERENCES contractors (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (accaunt_id) REFERENCES accounts (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (expense_id) REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  FOREIGN KEY (income_id) REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;
