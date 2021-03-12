DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

create table users(
	username varchar(250) not null primary key,
	password varchar(250) not null,
	enabled boolean not null default true
);

create table authorities (
	username varchar(250) not null,
	authority varchar(250) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO users (username, password) VALUES
  ('user1', '$2a$10$i4PbAabua2jYkrcLEHRLGOKqraLwldmfWqt1/D.pR/Weh4JK2SotG');

INSERT INTO authorities (username, authority) VALUES
  ('user1', 'ROLE_ADMIN');

DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    id int auto_increment primary key,
    title VARCHAR(250) NOT NULL,
    director VARCHAR(250) NOT NULL
);

INSERT INTO movies(title, director) VALUES
    ('titanic', 'james cameron');