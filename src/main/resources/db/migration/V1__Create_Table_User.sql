CREATE TABLE user_tb(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(180) NOT NULL,
    last_name VARCHAR(180) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL);