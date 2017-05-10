DROP TABLE meansflow IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE meansflow_types IF EXISTS;
DROP TABLE users_budgets IF EXISTS;
DROP TABLE budgets IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;
DROP SEQUENCE meansflow_types_sequence IF EXISTS;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;
CREATE SEQUENCE meansflow_types_sequence AS INTEGER START WITH 1;

CREATE TABLE users
(
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  user_name               text NOT NULL,
  email                   text NOT NULL,
  password                text NOT NULL,
  user_icon               OID,
  enabled                 BOOL DEFAULT TRUE,
  
  CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE user_roles
(
  user_id          INTEGER NOT NULL,
  role             text,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meansflow_types
(
  id                          INTEGER PRIMARY KEY DEFAULT nextval('meansflow_types_sequence'),
  meansflow_type_name         text,
  CONSTRAINT meansflow_type_name_idx UNIQUE (meansflow_type_name)
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE budgets (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  budget_name             text NOT NULL,
  budget_creator_id       INTEGER NOT NULL,
  initial_budget_amount   INTEGER,
  budget_per_day          INTEGER,
  description             TEXT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (budget_creator_id, budget_name),
  FOREIGN KEY (budget_creator_id) REFERENCES users (id) ON DELETE CASCADE
);

-- many to many relationship, additional table
CREATE TABLE users_budgets (
  user_id                 INTEGER NOT NULL,
  budget_id               INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  CONSTRAINT users_budgets_idx UNIQUE (user_id, budget_id)
);

CREATE TABLE meansflow (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP DEFAULT now(),
  last_update             TIMESTAMP,
  description             TEXT NOT NULL,
  operation_date_time     TIMESTAMP NOT NULL DEFAULT now(),
  amount                  INTEGER NOT NULL,
  budget_id           	  INTEGER NOT NULL,
  user_id	              INTEGER,
  meansflow_type_id       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  FOREIGN KEY (meansflow_type_id) REFERENCES meansflow_types (id)
);
