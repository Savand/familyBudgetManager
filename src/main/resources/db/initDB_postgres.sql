DROP TABLE IF EXISTS fundsflows;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS budgets_users;
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
  user_icon               OID,
  enabled                 BOOL DEFAULT TRUE,
  
  CONSTRAINT unique_email UNIQUE (email)
);

CREATE TABLE users_roles
(
  user_id          INTEGER NOT NULL,
  role             text,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE budgets (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP  DEFAULT now(),
  last_update             TIMESTAMP,
  budget_name             text NOT NULL,
  budget_creator_id       INTEGER NOT NULL,
  budget_per_day          INTEGER,
  description             TEXT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (budget_creator_id, budget_name),
  FOREIGN KEY (budget_creator_id) REFERENCES users (id) ON DELETE CASCADE
);

-- many to many relationship, additional table
CREATE TABLE budgets_users (
  budget_id               INTEGER NOT NULL,
  user_id                 INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE,
  CONSTRAINT budgets_users_idx UNIQUE (user_id, budget_id)
);

CREATE TABLE fundsflows (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date           TIMESTAMP DEFAULT now(),
  last_update             TIMESTAMP,
  description             TEXT NOT NULL,
  operation_date_time     TIMESTAMP NOT NULL DEFAULT now(),
  amount                  INTEGER NOT NULL,
  budget_id           	  INTEGER NOT NULL,
  user_id	              INTEGER,
  funds_flow_type          TEXT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
  FOREIGN KEY (budget_id) REFERENCES budgets (id) ON DELETE CASCADE
);
