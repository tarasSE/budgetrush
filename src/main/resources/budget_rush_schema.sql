CREATE TABLE users (
  id       INT AUTO_INCREMENT    NOT NULL,
  name     VARCHAR(20) UNIQUE    NOT NULL,
  password VARCHAR(50)           NOT NULL,
  role     INT(1)                NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE currencies (
  id         INT AUTO_INCREMENT NOT NULL,
  name       VARCHAR(15)        NOT NULL,
  short_name VARCHAR(3)         NOT NULL,
  code       INT(4)             NOT NULL,
  symbol     CHAR(1)            NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE accounts (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(50)        NOT NULL,
  user_id     INT                NOT NULL,
  currency_id INT                NOT NULL,
  balance     DECIMAL(10, 2)     NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (currency_id) REFERENCES currencies (id)
)
  ENGINE = InnoDB;


CREATE TABLE categories (
  id      INT AUTO_INCREMENT NOT NULL,
  name    VARCHAR(50)        NOT NULL,
  parent  INT,
  user_id INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (parent) REFERENCES categories (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

CREATE TABLE contractors (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(50)        NOT NULL,
  description VARCHAR(100),
  user_id     INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

CREATE TABLE orders (
  id                  INT AUTO_INCREMENT NOT NULL,
  amount              DECIMAL(10, 2)     NOT NULL,
  date                TIMESTAMP          NOT NULL,
  type                INT(1)             NOT NULL,
  account_id          INT DEFAULT NULL,
  transfer_account_id INT DEFAULT NULL,
  category_id         INT DEFAULT NULL,
  contractor_id       INT,
  expense_id          INT,
  income_id           INT,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id),
  FOREIGN KEY (transfer_account_id) REFERENCES accounts (id),
  FOREIGN KEY (category_id) REFERENCES categories (id),
  FOREIGN KEY (contractor_id) REFERENCES contractors (id),
  FOREIGN KEY (expense_id) REFERENCES orders (id),
  FOREIGN KEY (income_id) REFERENCES orders (id)
)
  ENGINE = InnoDB;

