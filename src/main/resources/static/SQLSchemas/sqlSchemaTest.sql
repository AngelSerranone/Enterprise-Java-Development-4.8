drop SCHEMA IF EXISTS lab4_8_test;
CREATE SCHEMA lab4_8_test;
USE lab4_8_test;

create table author (
	id bigint auto_increment not null,
    name varchar(255),
    primary key (id)
);

create table post (
	id bigint auto_increment not null,
    author_id bigint,
    title varchar(255),
    post text,
    primary key (id),
    foreign key (author_id) references author(id)
);

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT NOT NULL,
  username VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE role (
  id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(255),
  user_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into author (name) values 
	('Aiko Tanaka'),
	('Jonas Schmidt'),
	('Cas Van Dijk');
    
insert into post (author_id, title, post) values
	(1, 'Boost Your Productivity With 10 Easy Tips', 'Productivity - weoiuflsdnf qwiopuwofbnsdo iqwuefp jdpoajpofpofupao jpasof jsdophsdopf pohapofh sdpof'),
	(1, 'How to Focus', 'Foucs weoiuflsdnf qwiopuwofbnsdo iqwuefp jdpoajpofpofupao jpasof jsdophsdopf pohapofh sdpof'),
	(2, 'Learn to Speed Read in 30 Days', 'Knowledge weoiuflsdnf qwiopuwofbnsdo iqwuefp jdpoajpofpofupao jpasof jsdophsdopf pohapofh sdpof');
    
insert into user (username, password) values
	('pepe', '$2a$10$KRA4uO9WqDvLr/40/3moZejJe1/Vq4S6uf3sGiq29n57pb4SVdYLO'),
    ('paco', '$2a$10$KRA4uO9WqDvLr/40/3moZejJe1/Vq4S6uf3sGiq29n57pb4SVdYLO'),
    ('manolo', '$2a$10$KRA4uO9WqDvLr/40/3moZejJe1/Vq4S6uf3sGiq29n57pb4SVdYLO');
    
insert into role (name, user_id) values
	('ADMIN', 1),
	('CONTRIBUTOR', 1),
	('ADMIN', 2),
	('USER', 3);