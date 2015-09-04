INSERT INTO categories (name, parent) VALUES ('test_category', NULL );
INSERT INTO categories (name, parent) VALUES ('test_category_1', 1);
INSERT INTO categories (name, parent) VALUES ('test_category_2', 1);
INSERT INTO categories (name, parent) VALUES ('test_category_3', 1);
INSERT INTO categories (name, parent) VALUES ('test_category_4', 1);
INSERT INTO categories (name, parent) VALUES ('transfer', NULL );

INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency', 'TC0', 0000, '!');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_1', 'TC1', 1111, '@');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_2', 'TC2', 2222, '#');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_3', 'TC3', 3333, '$');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_4', 'TC4', 4444, '%');

INSERT INTO contractors (name, description) VALUES ('test_contractor', 'test_description');
INSERT INTO contractors (name, description) VALUES ('test_contractor_1', 'test_description_1');
INSERT INTO contractors (name, description) VALUES ('test_contractor_2', 'test_description_2');
INSERT INTO contractors (name, description) VALUES ('test_contractor_3', 'test_description_3');
INSERT INTO contractors (name, description) VALUES ('test_contractor_4', 'test_description_4');

INSERT INTO users (name, pass)  VALUES ('test_user', '0000');
INSERT INTO users (name, pass)  VALUES ('test_user_1', '1111');
INSERT INTO users (name, pass)  VALUES ('test_user_2', '2222');
INSERT INTO users (name, pass)  VALUES ('test_user_3', '3333');
INSERT INTO users (name, pass)  VALUES ('test_user_4', '4444');


INSERT INTO accounts (name, user_id, currency_id) VALUES ('test_account', 1, 1);
INSERT INTO accounts (name, user_id, currency_id) VALUES ('test_account_1', 2, 2);
INSERT INTO accounts (name, user_id, currency_id) VALUES ('test_account_2', 3, 3);
INSERT INTO accounts (name, user_id, currency_id) VALUES ('test_account_3', 4, 4);
INSERT INTO accounts (name, user_id, currency_id) VALUES ('test_account_4', 5, 5);

/*Ordes*/
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 0, 1, 1, 1, NULL , NULL );
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (2222.00, '2015-09-04 18:07:46', 0, 2, 2, 2, NULL , NULL );
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (3333.00, '2015-09-04 18:07:46', 0, 3, 3, 3, NULL , NULL );
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (4444.00, '2015-09-04 18:07:46', 0, 4, 4, 4, NULL , NULL );
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (5555.00, '2015-09-04 18:07:46', 0, 5, 5, 5, NULL , NULL );

/*Transfers*/
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 1, 1, 6, 1, 1 , 2 );
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 1, 1, 6, 1, 3 , 4);


