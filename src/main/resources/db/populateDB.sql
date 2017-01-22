
DELETE FROM incomes;
DELETE FROM outcomes;
DELETE FROM goods_type;
DELETE FROM users_budgets;
DELETE FROM user_roles;
DELETE FROM budgets;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

-- admin
INSERT INTO users (user_name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

-- user1
INSERT INTO users (user_name, email, password)
VALUES ('User1', 'user1@gmail.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- user2
INSERT INTO users (user_name, email, password)
VALUES ('User2', 'user2@gmail.com', '$2a$10$Sh0ZD2NFrzSDGBMGDBMijaoiebinlkdnbgADFKBAKDGbpmbgdpadk');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002);

  INSERT INTO budgets(budget_name, user_creator_id, description)
    VALUES ('admin budget', 100000, 'budget control of admins money');
    
  INSERT INTO budgets(budget_name, user_creator_id, description)
    VALUES ('users budget', 100001, 'budget control of users family money');
    
  INSERT INTO goods_type (goods_type_name) VALUES ('food'),            --100005
  												  ('entertainment'),   --100006
  												  ('clothes'),         --100007
  												  ('services'),        --100008
  												  ('healthcare'),      --100009
  												  ('presents'),        --100010
  												  ('houshold'),        --100011
  												  ('savings'),         --100012
  												  ('charity'),         --100013
  												  ('transport'),       --100014
  												  ('other');           --100015
  INSERT INTO users_budgets (user_id, budget_id)
    VALUES (100000, 100003), (100001, 100004), (100002, 100004);
    
  INSERT INTO incomes(description, operation_date_time, amount, budget_id, user_id)
    VALUES ('admins salary', '2017-01-10 10:00:00', 25000, 100003, 100000);
    
  INSERT INTO incomes(description, operation_date_time, amount, budget_id, user_id)
    VALUES ('user1 salary', '2017-01-10 10:00:00', 15000, 100004, 100001);
  
  INSERT INTO incomes(description, operation_date_time, amount, budget_id, user_id)
    VALUES ('user2 salary', '2017-01-10 10:00:00', 15000, 100004, 100002);
 
	--admin's outcomes    
  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('fish, potatoe, etc', '2017-01-10 10:00:00', 200, 100003, 100000, 100005);
    
  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('Wollies', '2017-02-10 10:00:00', 250, 100003, 100000, 100006);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gsm mobile phone', '2017-02-10 10:00:00', 100, 100003, 100000, 100008);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gamepad', '2017-10-10 10:00:00', 1200, 100003, 100000, 100010);

    --users outcomes
  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('noodles, meat', '2017-01-10 10:00:00', 100, 100004, 100001, 100005);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gun', '2017-01-10 10:00:00', 5000, 100004, 100001, 100015);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('rubber duck', '2017-06-10 10:00:00', 20, 100004, 100002, 100011);
