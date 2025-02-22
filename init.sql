CREATE SCHEMA IF NOT EXISTS `lesson4`;

USE `lesson4`;

CREATE TABLE IF NOT EXISTS user
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    phoneNumber CHAR(12) NULL,
    password VARCHAR(100) NOT NULL,

    unique index(email)
);

GRANT ALL PRIVILEGES ON lesson4.* TO 'user'@'localhost';
