DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int primary key auto_increment,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL

);

INSERT INTO users (username, password) VALUES
  ('user1', '123456');