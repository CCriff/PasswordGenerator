DROP SCHEMA IF EXISTS PasswordGeneratorSchema;
CREATE SCHEMA PasswordGeneratorSchema;
USE PasswordGeneratorSchema;

CREATE TABLE Users
(
    ID              int          NOT NULL AUTO_INCREMENT,
    Name            varchar(50)  NOT NULL,
    Email           varchar(100) NOT NULL UNIQUE,
    Hashed_password varchar(255) NOT NULL,
    Created_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Accounts
(
    ID                      int                                                                                                                                NOT NULL AUTO_INCREMENT,
    Account_type            enum ('EMAIL', 'BANK', 'SHOPPING', 'SOCIAL_MEDIA', 'WORK', 'PERSONAL', 'PRIVATE', 'BUSINESS', 'ANONYMOUS', 'GENERAL', 'CATCH_ALL') NOT NULL,
    Account_username        varchar(50)                                                                                                                        NOT NULL UNIQUE,
    Hashed_account_password varchar(255)                                                                                                                       NOT NULL,
    Expiry_date             datetime                                                                                                                           NOT NULL,
    User_id                 int                                                                                                                                NOT NULL,
    Created_at              datetime                                                                                                                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_at              datetime                                                                                                                           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (User_id) REFERENCES Users (ID) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE History
(
    ID                  int          NOT NULL AUTO_INCREMENT,
    Old_hashed_password varchar(255) NOT NULL,
    New_hashed_password varchar(255) NOT NULL,
    Account_id          int          NOT NULL,
    Created_at          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (Account_id) REFERENCES Accounts (ID) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE password_generation_information
(
    id                  int                                                                               NOT NULL AUTO_INCREMENT,
    password_length     int                                                                               NOT NULL,
    password_complexity enum ('SIMPLE', 'MODERATE', 'STRONG')                                             NOT NULL,
    expiry_policy       enum ('NEVER', 'DAILY', 'WEEKLY', 'SEMIWEEKLY', 'MONTHLY', 'QUARTERLY', 'YEARLY') NOT NULL,
    created_at          timestamp                                                                         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE Failed_login_attempts
(
    ID               int         NOT NULL AUTO_INCREMENT,
    Account_username varchar(50) NOT NULL,
    Failed_attempts  int                  DEFAULT 0 NOT NULL,
    Created_at       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    UNIQUE KEY (Account_username)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE Password_reset_requests
(
    ID              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    Email           varchar(100)    NOT NULL,
    Token           varchar(255)    NOT NULL,
    Expiration_date datetime        NOT NULL,
    Used            tinyint         NOT NULL DEFAULT 0,
    Created_at      datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    UNIQUE KEY (Token)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TRIGGER Failed_login_limit
    AFTER INSERT
    ON Failed_login_attempts
    FOR EACH ROW
BEGIN
    DECLARE @limit INT DEFAULT 5;
    DECLARE @count INT;
    SELECT @count = COUNT(*)
    FROM Failed_login_attempts
    WHERE Account_ID = NEW.Account_ID;

    IF @count >= @limit THEN
        UPDATE Accounts
        SET Account_status = 'locked'
        WHERE ID = NEW.Account_ID;
    END IF;
END;

COMMIT;
