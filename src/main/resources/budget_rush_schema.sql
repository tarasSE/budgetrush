CREATE TABLE users (
  id       INT AUTO_INCREMENT    NOT NULL,
  name     VARCHAR(20) UNIQUE    NOT NULL,
  password VARCHAR(50)           NOT NULL,
  email    VARCHAR(20)           NOT NULL,
  role     INT(1)                NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE groups (
  id   INT AUTO_INCREMENT NOT NULL,
  name VARCHAR(20)        NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE users_groups (
  group_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (group_id, user_id),
  FOREIGN KEY (group_id) REFERENCES groups (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
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
  id             INT AUTO_INCREMENT NOT NULL,
  name           VARCHAR(50)        NOT NULL,
  group_id       INT                NOT NULL,
  currency_id    INT                NOT NULL,
  currentBalance DECIMAL(12, 2)     NOT NULL,
  initBalance    DECIMAL(12, 2)     NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (group_id) REFERENCES groups (id),
  FOREIGN KEY (currency_id) REFERENCES currencies (id)
)
  ENGINE = InnoDB;

BEGIN;
CREATE TABLE categories (
  id         INT AUTO_INCREMENT NOT NULL,
  name       VARCHAR(50)        NOT NULL,
  predefined BOOLEAN,
  parent     INT,
  user_id    INT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent) REFERENCES categories (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

INSERT INTO categories (name, predefined) VALUES ('Food', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Clothes', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Transport', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Health', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Rent', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Other', TRUE);

COMMIT;

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
  id                 INT AUTO_INCREMENT NOT NULL,
  amount             DECIMAL(10, 2)     NOT NULL,
  date               TIMESTAMP          NOT NULL,
  type               INT(1)             NOT NULL,
  account_id         INT DEFAULT NULL,
  transferAccount_id INT DEFAULT NULL,
  category_id        INT DEFAULT NULL,
  contractor_id      INT,
  expense_id         INT,
  income_id          INT,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES accounts (id),
  FOREIGN KEY (transferAccount_id) REFERENCES accounts (id),
  FOREIGN KEY (category_id) REFERENCES categories (id),
  FOREIGN KEY (contractor_id) REFERENCES contractors (id),
  FOREIGN KEY (expense_id) REFERENCES orders (id)
    ON DELETE SET NULL,
  FOREIGN KEY (income_id) REFERENCES orders (id)
    ON DELETE SET NULL
)
  ENGINE = InnoDB;

CREATE TABLE budgets (
  id          INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(40),
  amount      DECIMAL(10, 2),
  startDate   DATE,
  endDate     DATE,
  category_id INT                NOT NULL,
  group_id    INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  ENGINE = InnoDB;

