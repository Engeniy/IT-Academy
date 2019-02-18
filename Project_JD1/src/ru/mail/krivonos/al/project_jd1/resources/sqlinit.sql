CREATE TABLE Permission
(
  id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(31)                    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name)
);
CREATE TABLE Role
(
  id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(31)                    NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE Role_permission
(
  role_id       BIGINT UNSIGNED NOT NULL,
  permission_id BIGINT UNSIGNED NOT NULL,
  FOREIGN KEY (role_id) REFERENCES Role (id),
  FOREIGN KEY (permission_id) references Permission (id),
  UNIQUE (role_id, permission_id)
);
CREATE TABLE User
(
  id       BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  email    VARCHAR(31)                    NOT NULL,
  surname  VARCHAR(31)                    NOT NULL,
  name     VARCHAR(31)                    NOT NULL,
  password VARCHAR(15)                    NOT NULL,
  role_id  BIGINT                         NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES Role (id)
);
CREATE TABLE Profile
(
  user_id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  address   VARCHAR(63)                    NOT NULL,
  telephone VARCHAR(31)                    NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id)
);
CREATE TABLE Item
(
  id            BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name          VARCHAR(31)                    NOT NULL,
  description   TEXT                           NOT NULL,
  unique_number VARCHAR(31)                    NOT NULL,
  price         DECIMAL UNSIGNED               NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (unique_number)
);
CREATE TABLE Order_state
(
  id    BIGINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(31)                    NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name)
);
CREATE TABLE `Order`
(
  user_id  BIGINT UNSIGNED NOT NULL,
  item_id  BIGINT UNSIGNED NOT NULL,
  state_id BIGINT UNSIGNED NOT NULL,
  created  DATETIME        NOT NULL,
  quantity INT UNSIGNED    NOT NULL,
  FOREIGN KEY (user_id) REFERENCES User (id),
  FOREIGN KEY (item_id) REFERENCES Item (id),
  FOREIGN KEY (state_id) REFERENCES Order_state (id)
);