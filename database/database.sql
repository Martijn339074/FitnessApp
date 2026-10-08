create database if not exists fitnessapp;

create table if not exists users (
    id serial primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null,
);

create table if not exists sporters (
    id serial primary key,
    name varchar(255) not null,
    age int not null,
    gender varchar(255) not null,
    email varchar(255) not null,
    phone varchar(255) not null,
    address varchar(255) not null,
);

INSERT INTO users (username, password, email, phone, address)
VALUES
  ('john', 'password', 'john@email.com', '0612345678', 'Utrecht'),
  ('jane', 'password', 'jane@email.com', '0687654321', 'Amsterdam');

INSERT INTO sporters (name, age, gender, email, phone, address)
VALUES
  ('John Doe', 25, 'Male', 'john@email.com', '0612345678', 'Utrecht'),
  ('Jane Smith', 30, 'Female', 'jane@email.com', '0687654321', 'Amsterdam');