DROP TABLE IF EXISTS meansflow;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meansflow_types;
DROP TABLE IF EXISTS users_budgets;
DROP TABLE IF EXISTS budgets;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP  DEFAULT now(),
  last_update_date_time   TIMESTAMP,
  user_name               text NOT NULL,
  email                   text NOT NULL,
  password                text NOT NULL,
  user_icon               BYTEA,
  enabled                 BOOL DEFAULT TRUE
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
  id                          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  meansflow_type_name         text,
  CONSTRAINT meansflow_type_name_idx UNIQUE (meansflow_type_name)
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE budgets (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP DEFAULT now(),
  last_update_date_time   TIMESTAMP,
  budget_name             text NOT NULL,
  user_creator_id         INTEGER NOT NULL,
  description             TEXT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (user_creator_id, budget_name),
  FOREIGN KEY (user_creator_id) REFERENCES users (id)
);

-- many to many relationship, additional table
CREATE TABLE users_budgets (
  user_id                 INTEGER NOT NULL,
  budget_id               INTEGER NOT NULL,
  CONSTRAINT users_budgets_idx UNIQUE (user_id, budget_id),
  FOREIGN KEY (budget_id) REFERENCES budgets (id),
  CONSTRAINT budget_user_idx UNIQUE (user_id, budget_id)
);

CREATE TABLE meansflow (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP DEFAULT now(),
  last_update_date_time   TIMESTAMP,
  description             TEXT NOT NULL,
  operation_date_time     TIMESTAMP NOT NULL,
  amount                  INT NOT NULL,
  budget_id           	  INT NOT NULL,
  user_id	              INT NOT NULL,
  meansflow_type_fk       INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  FOREIGN KEY (meansflow_type_fk) REFERENCES meansflow_types (id)
);
