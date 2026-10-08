-- PostgreSQL schema for FitnessApp (database: fitnessapp)
-- Run: docker exec -i fitnessapp-db psql -U fitnessuser -d fitnessapp < database/database.sql

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS sporters (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE REFERENCES users(id),
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password, email, phone, address)
VALUES
  ('john', 'password', 'john@email.com', '0612345678', 'Utrecht'),
  ('jane', 'password', 'jane@email.com', '0687654321', 'Amsterdam')
ON CONFLICT (username) DO NOTHING;

INSERT INTO sporters (user_id, name, age, gender)
VALUES
  (1, 'John Doe', 25, 'Male'),
  (2, 'Jane Smith', 30, 'Female')
ON CONFLICT (user_id) DO NOTHING;
