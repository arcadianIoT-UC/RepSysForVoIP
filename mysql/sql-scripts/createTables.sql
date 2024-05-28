CREATE TABLE ip_score (
id int AUTO_INCREMENT PRIMARY KEY,
IP_address  varchar(25) NOT NULL,
score DOUBLE NOT NULL
);

CREATE TABLE user_score (
id int AUTO_INCREMENT PRIMARY KEY,
user  varchar(25) NOT NULL,
score DOUBLE NOT NULL
);