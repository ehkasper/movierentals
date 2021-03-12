DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int auto_increment primary key,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL

);

INSERT INTO users (username, password) VALUES
  ('user1', '123456');

DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    id int auto_increment primary key,
    title VARCHAR(250) NOT NULL,
    director VARCHAR(250) NOT NULL
);

INSERT INTO movies(title, director) VALUES
    ('titanic', 'james cameron');