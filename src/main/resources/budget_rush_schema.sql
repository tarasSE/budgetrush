CREATE TABLE users (
  id       SERIAL NOT NULL,
  name     VARCHAR UNIQUE    NOT NULL,
  password VARCHAR           NOT NULL,
  email    VARCHAR           NOT NULL,
  role     INT               NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE groups (
  id   SERIAL NOT NULL,
  name VARCHAR        NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_groups (
  group_id INT NOT NULL,
  user_id  INT NOT NULL,
  PRIMARY KEY (group_id, user_id),
  FOREIGN KEY (group_id) REFERENCES groups (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE currencies (
  id         SERIAL NOT NULL,
  name       VARCHAR        NOT NULL,
  short_name VARCHAR         NOT NULL,
  code       INT       NOT NULL,
  symbol     CHAR            NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE accounts (
  id             SERIAL NOT NULL,
  name           VARCHAR        NOT NULL,
  group_id       INT                NOT NULL,
  currency_id    INT                NOT NULL,
  currentBalance DECIMAL(12, 2)     NOT NULL,
  initBalance    DECIMAL(12, 2)     NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (group_id) REFERENCES groups (id),
  FOREIGN KEY (currency_id) REFERENCES currencies (id)
);

BEGIN;
CREATE TABLE categories (
  id         SERIAL NOT NULL,
  name       VARCHAR        NOT NULL,
  predefined BOOLEAN,
  parent     INT,
  user_id    INT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent) REFERENCES categories (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO categories (name, predefined) VALUES ('Food', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Clothes', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Transport', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Health', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Rent', TRUE);
INSERT INTO categories (name, predefined) VALUES ('Other', TRUE);

COMMIT;

CREATE TABLE contractors (
  id          SERIAL NOT NULL,
  name        VARCHAR       NOT NULL,
  description VARCHAR,
  user_id     INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE orders (
  id                 SERIAL NOT NULL,
  amount             DECIMAL(10, 2)     NOT NULL,
  date               TIMESTAMP          NOT NULL,
  type               INT            NOT NULL,
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
);

CREATE TABLE budgets (
  id          SERIAL NOT NULL,
  name        VARCHAR,
  amount      DECIMAL(10, 2),
  startDate   DATE,
  endDate     DATE,
  category_id INT                NOT NULL,
  group_id    INT                NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
);

