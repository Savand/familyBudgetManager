
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

-- user
INSERT INTO users (user_name, email, password)
VALUES ('User', 'user@gmail.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001);

  INSERT INTO budgets(budget_name, user_creator_id, description)
    VALUES ('admin budget', 100000, 'budget to save admin familys money');
    
  INSERT INTO budgets(budget_name, user_creator_id, description)
    VALUES ('user"s budget', 100001, 'budget to save user family money');
    
  INSERT INTO goods_type (goods_type_name) VALUES ('victuals'),        --100004
  												  ('entertainment'),   --100005
  												  ('clothes'),         --100006
  												  ('services'),        --100007
  												  ('healthcare'),      --100008
  												  ('presents'),        --100009
  												  ('houshold'),        --100010
  												  ('savings'),         --100011
  												  ('charity'),         --100012
  												  ('transport'),       --100013
  												  ('other');           --100014
  INSERT INTO users_budgets (user_id, budget_id)
    VALUES (100000, 100002), (100001, 100003);
    
  INSERT INTO incomes(description, operation_date_time, amount, budget_id, user_id)
    VALUES ('salary', '2017-01-10 10:00:00', 15000, 100002, 100000);
    
  INSERT INTO incomes(description, operation_date_time, amount, budget_id, user_id)
    VALUES ('salary', '2017-01-10 10:00:00', 15000, 100003, 100001);
    
  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('fish, potatoe, etc', '2017-01-10 10:00:00', 200, 100002, 100000, 100004);
    
--TODO finish with outcomes

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('Wollies', '2017-02-10 10:00:00', 250, 100002, 100000, 100005);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gsm mobile phone', '2017-02-10 10:00:00', 100, 100002, 100000, 100007);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gamepad', '2017-10-10 10:00:00', 1200, 100002, 100000, 100009);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('noodles, meat', '2017-01-10 10:00:00', 100, 100003, 100001, 100004);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('gun', '2017-01-10 10:00:00', 5000, 100003, 100001, 100014);

  INSERT INTO outcomes(description, operation_date_time, amount, budget_id, user_id, type_outcome)
    VALUES ('rubber duck', '2017-06-10 10:00:00', 20, 100003, 100001, 100010);
