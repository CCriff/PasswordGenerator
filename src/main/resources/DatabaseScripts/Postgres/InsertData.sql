-- Users Table
INSERT INTO users (name, email, password)
VALUES ('John Doe', 'johndoe@example.com', '$2y$10$I/nE/KjxD8JvR/N/oIjXK..BpfjU6R0y0bYozEi00Lsxq3sxjKsJ2'),
       ('Jane Doe', 'janedoe@example.com', '$2y$10$Kx.vOgCpB7E9gFJvFc5PW8.u0jK4e4piu5g5F0Asln2jX0Sb7S.pm'),
       ('Jim Smith', 'jimsmith@example.com', '$2y$10$X9Ot.I54j6RlU619jK6JYOIVTirTQ2sZKqLRmn1mJU6McmAfjKzN6');

-- Accounts Table
INSERT INTO accounts (account_type, account_username, account_password, expiry_date, user_id)
VALUES ('Work', 'johndoe', '@MyWorkPass12345678', '2022-06-01', 1),
       ('School', 'janedoe', '@MySchoolPass12345678', '2022-06-01', 2),
       ('Financial', 'jimsmith', '@MyFinanceAccountPass12345678', '2022-06-01', 3),
       ('Anonymous', 'anonymoususer', '@MyAnonymousAxxountPass12345678', '2022-06-01', 4);

-- History Table
INSERT INTO history (old_password, new_password, account_id)
VALUES ('@MyWorkPass12345678', '@MyWorkPass87654321', 1),
       ('@MySchoolPass12345678', '@MySchoolPass87654321', 2),
       ('@MyFinanceAccountPass12345678', '@MyFinanceAccountPass87654321', 3),
       ('@MyAnonymousAxxountPass12345678', '@MyAnonymousAxxountPass87654321', 4);

-- Failed Login Attempts Table
INSERT INTO failed_login_attempts (email, attempts)
VALUES ('johndoe@example.com', 5),
       ('janedoe@example.com', 3),
       ('jimsmith@example.com', 2);

-- Password Reset Requests Table
INSERT INTO password_reset_requests (email, token)
VALUES ('johndoe@example.com', 'f3f3d3c9-05a2-4c26-b076-5f5c5f5e5e5f'),
       ('janedoe@example.com', 'f3f3d3c9-05a2-4c26-b076-5f5c5f5e5e5g'),
       ('jimsmith@example.com', 'f3f3d3c9-05a2-4c26-b076-5f5c5f5e5e5h');

-- Password Generation Info Table
INSERT INTO password_generation_info (length, complexity, expiry_ date, user_id)
VALUES (8, 'strong', '2022-06-01', 1),
       (10, 'medium', '2022-06-01', 2),
       (9, 'weak', '2022-06-01', 3),
       (8, 'strong', '2022-06-01', 4);