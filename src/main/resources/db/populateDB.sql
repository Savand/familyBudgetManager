DELETE FROM meansflow;
DELETE FROM meansflow_types;
DELETE FROM users_budgets;
DELETE FROM user_roles;
DELETE FROM budgets;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;
ALTER SEQUENCE meansflow_types_sequence RESTART WITH 1;

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

INSERT INTO budgets(budget_name, user_creator_id, description, initial_budget_amount, budget_per_day)
    VALUES ('admin budget', 100000, 'budget control of admins money', 50000, 600);
    
INSERT INTO budgets(budget_name, user_creator_id, description, initial_budget_amount, budget_per_day)
    VALUES ('users budget', 100001, 'budget control of users family money', 20000, 400);
    
INSERT INTO meansflow_types (meansflow_type_name) VALUES     ('food'),            --1
		  												     ('entertainment'),   --2
		  												     ('clothes'),         --3
		  												     ('services'),        --4
		  												     ('healthcare'),      --5
		  												     ('presents'),        --6
		  												     ('houshold'),        --7
		  												     ('savings'),         --8
		  												     ('charity'),         --9
		  												     ('transport'),       --10
		  												     ('income'),          --11
		  												     ('other');           --12
INSERT INTO users_budgets (user_id, budget_id)
    VALUES (100000, 100003), (100001, 100004), (100002, 100004);
    
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('admins salary', '2017-01-10 10:00:00', 25000, 100003, 100000, 11);
    
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('user1 salary', '2017-01-10 10:00:00', 15000, 100004, 100001, 11);
  
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('user2 salary', '2017-01-10 10:00:00', 15000, 100004, 100002, 11);
 
	--admin's outcomes    
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('fish, potatoe, etc', '2017-01-10 10:00:00', -200, 100003, 100000, 1);
    
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('Wollies', '2017-02-10 10:00:00', -250, 100003, 100000, 2);

INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('gsm mobile phone', '2017-02-10 10:00:00', -100, 100003, 100000, 4);

INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('gamepad', '2017-10-10 10:00:00', -1200, 100003, 100000, 2);

    --users outcomes
INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('noodles, meat', '2017-01-10 10:00:00', -100, 100004, 100001, 1);

INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('gun', '2017-01-10 10:00:00', -5000, 100004, 100001, 7);

INSERT INTO meansflow(description, operation_date_time, amount, budget_id, user_id, meansflow_type_id)
    VALUES ('rubber duck', '2017-06-10 10:00:00', -20, 100004, 100002, 7);
