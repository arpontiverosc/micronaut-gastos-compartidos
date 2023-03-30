INSERT INTO `group_expenses` (id, group_name) VALUES(1011, 'Group name');
INSERT INTO `group_expenses` (id, group_name) VALUES(1012, 'Group name 2');
INSERT INTO `group_expenses` (id, group_name) VALUES(1013, 'Group name 3');
INSERT INTO `group_expenses` (id, group_name) VALUES(1014, 'Group name 4');
INSERT INTO `group_expenses` (id, group_name) VALUES(1015, 'Group name 5');

INSERT INTO users (id, user_name) VALUES(100,'user0');
INSERT INTO users (id, user_name) VALUES(101,'user1');
INSERT INTO users (id, user_name) VALUES(102,'user2');
INSERT INTO users (id, user_name) VALUES(103,'user3');
INSERT INTO users (id, user_name) VALUES(104,'user4');
INSERT INTO users (id, user_name) VALUES(105,'user5');

INSERT INTO user_group (user_id, group_id) VALUES(100, 1011);
INSERT INTO user_group (user_id, group_id) VALUES(102, 1011);
INSERT INTO user_group (user_id, group_id) VALUES(103, 1011);

INSERT INTO balances (id, active, description, group_id) VALUES(120, 1,'Weekend in London', 1011);
INSERT INTO balances (id, active, description, group_id) VALUES(122, 1,'Trip to Paris', 1012);
INSERT INTO balances (id, active, description, group_id) VALUES(123, 1,'Barchelor party', 1013);
INSERT INTO balances (id, active, description, group_id) VALUES(124, 1,'Wedding', 1014);


INSERT INTO expenses (id, currency, description, expense_date, price, balance_id, group_id, user_id)
VALUES(1246,'€','Dinner at restaurant.', '2023-03-22 22:00:00', 123.34, 120, 1011, 100);

INSERT INTO expenses (id, currency, description, expense_date, price, balance_id, group_id, user_id)
VALUES(1247,'€','Drinks at bar.', '2022-03-22 23:00:00', 60.34, 120, 1011, 100);

INSERT INTO expenses (id, currency, description, expense_date, price, balance_id, group_id, user_id)
VALUES(1248,'€','Breakfast.', '2022-03-23 08:00:00', 30.88, 120, 1011, 100);

INSERT INTO expenses (id, currency, description, expense_date, price, balance_id, group_id, user_id)
VALUES(1249,'€','Museum tickets.', '2022-03-23 12:00:00', 50.24, 120, 1011, 102);

INSERT INTO expenses (id, currency, description, expense_date, price, balance_id, group_id, user_id)
VALUES(1250,'€','Underground tickets.', '2022-03-23 14:00:00', 20.76, 120, 1011, 102);