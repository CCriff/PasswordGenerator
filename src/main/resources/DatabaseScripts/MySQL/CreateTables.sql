CREATE TABLE Users
(
    ID         int(11)      NOT NULL AUTO_INCREMENT,
    Name       varchar(255) NOT NULL,
    Email      varchar(255) NOT NULL,
    Password   varchar(255) NOT NULL,
    Created_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE Accounts
(
    ID               int(11)      NOT NULL AUTO_INCREMENT,
    Account_type     varchar(255) NOT NULL,
    Account_username varchar(255) NOT NULL,
    Account_password varchar(255) NOT NULL,
    Expiry_date      datetime     NOT NULL,
    User_id          int(11)      NOT NULL,
    Created_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Updated_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (User_id) REFERENCES Users (ID)
);

CREATE TABLE History
(
    ID           int(11)      NOT NULL AUTO_INCREMENT,
    Old_password varchar(255) NOT NULL,
    New_password varchar(255) NOT NULL,
    Account_id   int(11)      NOT NULL,
    Created_at   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (Account_id) REFERENCES Accounts (ID)
);

CREATE TABLE Failed_login_attempts
(
    ID               int(11)      NOT NULL AUTO_INCREMENT,
    Account_username varchar(255) NOT NULL,
    Failed_attempts  int(11)      NOT NULL,
    Created_at       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE Password_reset_requests
(
    ID             int(11)      NOT NULL AUTO_INCREMENT,
    User_id        int(11)      NOT NULL,
    Request_status varchar(255) NOT NULL,
    Created_at     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID),
    FOREIGN KEY (User_id) REFERENCES Users (ID)
);

CREATE TABLE Password_generation_information
(
    ID                  int(11)      NOT NULL AUTO_INCREMENT,
    Password_length     int(11)      NOT NULL,
    Password_complexity varchar(255) NOT NULL,
    Expiry_policy       varchar(255) NOT NULL,
    Created_at          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);

