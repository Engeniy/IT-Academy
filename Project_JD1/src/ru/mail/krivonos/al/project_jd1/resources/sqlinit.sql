DROP TABLE IF EXISTS `Order`;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Profile;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Role_permission;
DROP TABLE IF EXISTS Role;
DROP TABLE IF EXISTS Permission;
CREATE TABLE IF NOT EXISTS Permission
(
  id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name ENUM ('CUSTOMER', 'SALES')         NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name)
);
CREATE TABLE IF NOT EXISTS Role
(
  id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name VARCHAR(31)                    NOT NULL
);
CREATE TABLE IF NOT EXISTS Role_Permission
(
  temp BOOLEAN
);
CREATE TABLE IF NOT EXISTS User
(
  id       BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
  email    VARCHAR(31)                    NOT NULL,
  surname  VARCHAR(31)                    NOT NULL,
  name     VARCHAR(31)                    NOT NULL,
  password VARCHAR(15)                    NOT NULL,
  UNIQUE (email)
);
CREATE TABLE IF NOT EXISTS Profile
(
  address   VARCHAR(63) NOT NULL,
  telephone VARCHAR(31) NOT NULL
);
CREATE TABLE IF NOT EXISTS Item
(
  id            BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
  name          VARCHAR(31)                    NOT NULL,
  description   TEXT                           NOT NULL,
  unique_number VARCHAR(63)                    NOT NULL,
  price         DECIMAL UNSIGNED               NOT NULL,
  deleted       BOOLEAN DEFAULT (FALSE)        NOT NULL,
  UNIQUE (unique_number)
);
CREATE TABLE IF NOT EXISTS `Order`
(
  created  DATETIME                                              NOT NULL,
  quantity INT UNSIGNED                                          NOT NULL,
  state    ENUM ('NEW', 'REVIEWING', 'IN_PROGRESS', 'DELIVERED') NOT NULL
);
ALTER TABLE Role_permission
  DROP COLUMN temp,
  ADD role_id BIGINT UNSIGNED NOT NULL,
  ADD FOREIGN KEY (role_id) REFERENCES Role (id),
  ADD permission_id BIGINT UNSIGNED NOT NULL,
  ADD FOREIGN KEY (permission_id) references Permission (id);
ALTER TABLE User
  ADD role_id BIGINT UNSIGNED NOT NULL,
  ADD FOREIGN KEY (role_id) REFERENCES Role (id);
ALTER TABLE Profile
  ADD user_id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  ADD FOREIGN KEY (user_id) REFERENCES User (id),
  ADD UNIQUE (user_id);
ALTER TABLE `Order`
  ADD user_id BIGINT UNSIGNED NOT NULL,
  ADD FOREIGN KEY (user_id) REFERENCES User (id),
  ADD item_id BIGINT UNSIGNED NOT NULL,
  ADD FOREIGN KEY (item_id) REFERENCES Item (id);


