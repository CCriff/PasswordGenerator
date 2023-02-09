-- Users Table
CREATE TABLE users
(
    id         serial PRIMARY KEY,
    name       varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now()
);

-- Accounts Table
CREATE TABLE accounts
(
    id               serial PRIMARY KEY,
    account_type     varchar(255) NOT NULL,
    account_username varchar(255) NOT NULL,
    account_password varchar(255) NOT NULL,
    expiry_date      timestamptz  NOT NULL,
    user_id          integer      NOT NULL REFERENCES users (id),
    created_at       timestamptz  NOT NULL DEFAULT now(),
    updated_at       timestamptz  NOT NULL DEFAULT now()
);

-- History Table
CREATE TABLE history
(
    id           serial PRIMARY KEY,
    old_password varchar(255) NOT NULL,
    new_password varchar(255) NOT NULL,
    account_id   integer      NOT NULL REFERENCES accounts (id),
    created_at   timestamptz  NOT NULL DEFAULT now()
);

-- Failed Login Attempts Table
CREATE TABLE failed_login_attempts
(
    id         serial PRIMARY KEY,
    email      varchar(255) NOT NULL,
    attempts   integer      NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now()
);

-- Password Reset Requests Table
CREATE TABLE password_reset_requests
(
    id         serial PRIMARY KEY,
    email      varchar(255) NOT NULL,
    token      varchar(255) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now()
);

-- Password Generation Info Table
CREATE TABLE password_generation_info
(
    id            serial PRIMARY KEY,
    length        integer      NOT NULL,
    complexity    varchar(255) NOT NULL,
    expiry_policy varchar(255) NOT NULL,
    created_at    timestamptz  NOT NULL DEFAULT now(),
    updated_at    timestamptz  NOT NULL DEFAULT now()
);