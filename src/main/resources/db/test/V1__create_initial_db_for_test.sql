CREATE TABLE USER_TBL (
	id SERIAL PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL
);

INSERT INTO USER_TBL (nickname, name, last_name) VALUES ('elkingtest1', 'Elkin Test', 'Giraldo Test');
