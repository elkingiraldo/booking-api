CREATE TABLE USER_TBL (
	id SERIAL PRIMARY KEY,
    email VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);

INSERT INTO USER_TBL (email, password) VALUES ('elkintest', 'elkinpasswordtest');
