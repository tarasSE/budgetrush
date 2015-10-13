INSERT INTO users (name, password, role) VALUES ('admin', '1', 1);
INSERT INTO users (name, password, role) VALUES ('user', '1', 0);
INSERT INTO users (name, password, role) VALUES ('test_user_2', '2222', 0);
INSERT INTO users (name, password, role) VALUES ('test_user_3', '3333', 0);
INSERT INTO users (name, password, role) VALUES ('test_user_4', '4444', 0);

INSERT INTO groups (name)  VALUES ('test_group_1');
INSERT INTO groups (name)  VALUES ('test_group_2');
INSERT INTO groups (name)  VALUES ('test_group_3');
INSERT INTO groups (name)  VALUES ('test_group_4');
INSERT INTO groups (name)  VALUES ('test_group_5');

INSERT INTO users_groups (group_id, user_id)  VALUES (1,1);
INSERT INTO users_groups (group_id, user_id)  VALUES (2,2);
INSERT INTO users_groups (group_id, user_id)  VALUES (3,3);
INSERT INTO users_groups (group_id, user_id)  VALUES (4,4);
INSERT INTO users_groups (group_id, user_id)  VALUES (5,5);

INSERT INTO categories (name, parent, user_id) VALUES ('test_category', NULL , 1);
INSERT INTO categories (name, parent, user_id) VALUES ('test_category_1', 1, 1);
INSERT INTO categories (name, parent, user_id) VALUES ('test_category_2', 1, 1);
INSERT INTO categories (name, parent, user_id) VALUES ('test_category_3', NULL, 2);
INSERT INTO categories (name, parent, user_id) VALUES ('test_category_4', NULL, 3);
INSERT INTO categories (name, parent, user_id) VALUES ('transfer', NULL, 4);

INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency', 'TC0', 0000, '!');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_1', 'TC1', 1111, '@');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_2', 'TC2', 2222, '#');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_3', 'TC3', 3333, '$');
INSERT INTO currencies (name, short_name, code, symbol) VALUES ('test_currency_4', 'TC4', 4444, '%');

INSERT INTO contractors (name, description, user_id) VALUES ('test_contractor_0', 'test_description_0', 5);
INSERT INTO contractors (name, description, user_id) VALUES ('test_contractor_1', 'test_description_1', 1);
INSERT INTO contractors (name, description, user_id) VALUES ('test_contractor_2', 'test_description_2', 2);
INSERT INTO contractors (name, description, user_id) VALUES ('test_contractor_3', 'test_description_3', 3);
INSERT INTO contractors (name, description, user_id) VALUES ('test_contractor_4', 'test_description_4', 4);


INSERT INTO accounts (name, group_id, currency_id, balance) VALUES ('test_account', 1, 1, 0);
INSERT INTO accounts (name, group_id, currency_id, balance) VALUES ('test_account_1', 2, 2, 0);
INSERT INTO accounts (name, group_id, currency_id, balance) VALUES ('test_account_2', 3, 3, 0);
INSERT INTO accounts (name, group_id, currency_id, balance) VALUES ('test_account_3', 4, 4, 0);
INSERT INTO accounts (name, group_id, currency_id, balance) VALUES ('test_account_4', 5, 5, 0);

/*Income orders*/
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 0, 1, 1, 1, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (2222.00, '2015-09-04 18:07:46', 0, 2, 2, 2, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (3333.00, '2015-09-04 18:07:46', 0, 3, 3, 3, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (4444.00, '2015-09-04 18:07:46', 0, 4, 4, 4, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (5555.00, '2015-09-04 18:07:46', 0, 5, 5, 5, NULL, NULL);


/*Expense orders*/
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (- 1111.00, '2015-09-04 18:07:46', 0, 1, 1, 1, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (- 2222.00, '2015-09-04 18:07:46', 0, 2, 2, 2, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (- 3333.00, '2015-09-04 18:07:46', 0, 3, 3, 3, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (- 4444.00, '2015-09-04 18:07:46', 0, 4, 4, 4, NULL, NULL);
INSERT INTO orders (amount, date, type, account_id, category_id, contractor_id, expense_id, income_id)
VALUES (- 5555.00, '2015-09-04 18:07:46', 0, 5, 5, 5, NULL, NULL);


/*Transfers*/
INSERT INTO orders (amount, date, type, account_id, transferAccount_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 1, 1, 2, 6, 1, 1, 2);
INSERT INTO orders (amount, date, type, account_id, transferAccount_id, category_id, contractor_id, expense_id, income_id)
VALUES (1111.00, '2015-09-04 18:07:46', 1, 1, 2, 6, 1, 3, 4);


/*Budgets */
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget1', 123456, '2014-09-04',  '2016-09-04', 1, 1);
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget2', 123456, '2014-09-04',  '2016-09-04', 1, 1);
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget3', 123456, '2014-09-04',  '2016-09-04', 1, 1);
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget4', 123456, '2014-09-04',  '2016-09-04', 1, 1);
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget5', 123456, '2014-09-04',  '2016-09-04', 1, 1);
INSERT INTO budgets (name, amount, startDate, endDate, category_id, user_id)
VALUES ('budget6', 123456, '2014-09-04',  '2016-09-04', 1, 1);


