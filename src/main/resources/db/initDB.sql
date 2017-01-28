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
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
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
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  budget_name             text NOT NULL,
  user_creator_id         INTEGER NOT NULL,
  initial_budget_amount   INTEGER,
  budget_per_day          INTEGER,
  description             TEXT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (user_creator_id, budget_name),
  FOREIGN KEY (user_creator_id) REFERENCES users (id) ON DELETE CASCADE
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
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  description             TEXT NOT NULL,
  operation_date_time     TIMESTAMP NOT NULL,
  amount                  INTEGER NOT NULL,
  budget_id           	  INTEGER NOT NULL,
  user_id	              INTEGER,
  meansflow_type_fk       INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  FOREIGN KEY (meansflow_type_fk) REFERENCES meansflow_types (id)
);
