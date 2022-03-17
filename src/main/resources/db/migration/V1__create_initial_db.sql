CREATE TABLE USER_TBL (
	id SERIAL PRIMARY KEY,
    nickname VARCHAR(128) NOT NULL,
    name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL
);

CREATE TABLE RESERVATION_TBL (
	id uuid DEFAULT random_uuid() PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    ts_created TIMESTAMP NOT NULL,
    ts_updated TIMESTAMP NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    user_id INTEGER NOT NULL
);

CREATE TABLE ROOM_RESERVED_TBL (
	id SERIAL PRIMARY KEY,
    reservetion_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE ROOM_TBL (
	id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    current_price DECIMAL(10,2) NOT NULL
);

INSERT INTO USER_TBL (nickname, name, last_name) VALUES ('elkingiraldo91', 'Elkin Giovanni', 'Giraldo Pinedo');

INSERT INTO ROOM_TBL (name, current_price) VALUES ('T2-303', 69000.5);
