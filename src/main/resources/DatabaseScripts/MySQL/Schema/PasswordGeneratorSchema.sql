DROP SCHEMA IF EXISTS PasswordGeneratorSchema;
CREATE SCHEMA PasswordGeneratorSchema;
USE
    PasswordGeneratorSchema;

CREATE TABLE User
(
    id        BIGINT(20)   NOT NULL AUTO_INCREMENT,
    username  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX username (username),
    UNIQUE INDEX email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE Account
(
    id                    BIGINT(20)   NOT NULL AUTO_INCREMENT,
    username              VARCHAR(255) NOT NULL,
    email                 VARCHAR(255) NOT NULL,
    password              VARCHAR(255) NOT NULL,
    lastPasswordResetDate TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    createdAt             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt             TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE INDEX username (username),
    UNIQUE INDEX email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE Failed_login_attempts
(
    id          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    username    VARCHAR(255) NOT NULL,
    attemptTime TIMESTAMP    NOT NULL,
    createdAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE History
(
    id          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    oldPassword VARCHAR(255) NOT NULL,
    newPassword VARCHAR(255) NOT NULL,
    account_id  BIGINT(20)   NOT NULL,
    createdAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Account (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;