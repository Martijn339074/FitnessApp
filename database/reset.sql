-- Wipes and recreates all FitnessApp tables (PostgreSQL).
-- Run: docker exec -i fitnessapp-db psql -U fitnessuser -d fitnessapp < database/reset.sql

DROP TABLE IF EXISTS sporters CASCADE;
DROP TABLE IF EXISTS exercises CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE sporters (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(255) NOT NULL
);

CREATE TABLE exercises (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    muscle_group VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO users (username, password, email, phone, address)
VALUES
  ('john', 'password', 'john@email.com', '0612345678', 'Utrecht'),
  ('jane', 'password', 'jane@email.com', '0687654321', 'Amsterdam');

INSERT INTO sporters (user_id, name, age, gender)
SELECT u.id, 'John Doe', 25, 'Male'
FROM users u WHERE u.username = 'john';

INSERT INTO sporters (user_id, name, age, gender)
SELECT u.id, 'Jane Smith', 30, 'Female'
FROM users u WHERE u.username = 'jane';

INSERT INTO exercises (name, muscle_group, description)
VALUES
  ('Push-up', 'Chest', 'Keep your body straight, lower your chest toward the floor, then push back up.'),
  ('Squat', 'Legs', 'Stand with your feet shoulder-width apart, lower your hips, then stand back up.'),
  ('Plank', 'Core', 'Hold a straight line from head to heels, supported on your forearms and toes.'),
  ('Pull-up', 'Back', 'Hang from a bar, pull your chin above it, then lower yourself with control.'),
  ('Lunge', 'Legs', 'Step forward, lower your back knee toward the floor, then push back to standing.');
