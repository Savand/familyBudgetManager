DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS goods_type;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS budgets;
DROP TABLE IF EXISTS users_budgets;
DROP TABLE IF EXISTS incomes;
DROP TABLE IF EXISTS outcomes;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE goods_type
(
  goods_type    VARCHAR,
  CONSTRAINT goods_type_idx UNIQUE (goods_type),
);

CREATE TABLE users
(
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP NOT NULL,
  last_update_date_time   TIMESTAMP NOT NULL,
  user_name               VARCHAR NOT NULL,
  email                   VARCHAR NOT NULL,
  password                VARCHAR NOT NULL,
  photo                   BYTEA,
  enabled                 BOOL DEFAULT TRUE,
);

CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE budgets (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP NOT NULL,
  last_update_date_time   TIMESTAMP NOT NULL,
  budget_name             VARCHAR NOT NULL,
  user_creator_id         INTEGER NOT NULL,
  description             TEXT NOT NULL,
  budgetAmount            INT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (user_creator_id, budget_name),
  FOREIGN KEY (user_creator_id) REFERENCES users (id)
);

-- many to many relationship, additional table
CREATE TABLE users_budgets (
  user_id                 INTEGER NOT NULL,
  budget_id               INTEGER NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (user_id, budget_id),
  FOREIGN KEY (user_id) REFERENCES users (id)
  FOREIGN KEY (budget_id) REFERENCES budgets (id)
);


--TODO add tables: Incomes, Outcomes
CREATE TABLE Incomes (
  id                      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  creation_date_time      TIMESTAMP NOT NULL,
  last_update_date_time   TIMESTAMP NOT NULL,
  budge_name             VARCHAR NOT NULL,
  user_creator_id         INTEGER NOT NULL,
  description             TEXT NOT NULL,
  budgetAmount            INT NOT NULL,
  CONSTRAINT budget_user_idx UNIQUE (user_creator_id, budget_name),
  FOREIGN KEY (user_creator_id) REFERENCES users (id)
);