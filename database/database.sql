-- PostgreSQL schema + seed for FitnessApp (database: fitnessapp)
-- For a clean setup on an existing DB, prefer: database/reset.sql
-- Fresh Docker volume: this file runs automatically via docker-compose.

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
    user_id INT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(255) NOT NULL,
    weight_kg NUMERIC(5, 1) NOT NULL,
    height_cm NUMERIC(5, 1) NOT NULL
);

CREATE TABLE IF NOT EXISTS coaches (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS exercises (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    muscle_group VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO users (username, password, email, phone, address)
VALUES
  ('john', 'password', 'john@email.com', '0612345678', 'Utrecht'),
  ('jane', 'password', 'jane@email.com', '0687654321', 'Amsterdam'),
  ('alex', 'password', 'alex@email.com', '0611111111', 'Rotterdam')
ON CONFLICT (username) DO NOTHING;

INSERT INTO sporters (user_id, name, age, gender, weight_kg, height_cm)
SELECT u.id, 'John Doe', 25, 'Male', 82.0, 180.0
FROM users u WHERE u.username = 'john'
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO sporters (user_id, name, age, gender, weight_kg, height_cm)
SELECT u.id, 'Jane Smith', 30, 'Female', 64.0, 168.0
FROM users u WHERE u.username = 'jane'
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO coaches (user_id, name, specialty)
SELECT u.id, 'Alex Coach', 'Strength'
FROM users u WHERE u.username = 'alex'
ON CONFLICT (user_id) DO NOTHING;

INSERT INTO exercises (name, muscle_group, description)
VALUES
  ('Push-up', 'Chest', 'Keep your body straight, lower your chest toward the floor, then push back up.'),
  ('Squat', 'Legs', 'Stand with your feet shoulder-width apart, lower your hips, then stand back up.'),
  ('Plank', 'Core', 'Hold a straight line from head to heels, supported on your forearms and toes.'),
  ('Pull-up', 'Back', 'Hang from a bar, pull your chin above it, then lower yourself with control.'),
  ('Lunge', 'Legs', 'Step forward, lower your back knee toward the floor, then push back to standing.')
ON CONFLICT (name) DO NOTHING;
