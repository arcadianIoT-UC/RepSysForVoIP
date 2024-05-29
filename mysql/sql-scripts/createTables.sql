CREATE TABLE ip_score (
IP_address  varchar(25) PRIMARY KEY,
score DOUBLE NOT NULL
);

CREATE TABLE user_score (
user  varchar(25) PRIMARY KEY,
score DOUBLE NOT NULL
);