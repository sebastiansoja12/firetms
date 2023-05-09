CREATE TABLE IF NOT EXISTS TRUCK (
plate varchar(255) NOT NULL,
brand varchar(255) NOT NULL,
model varchar(255) DEFAULT NULL,
weight BIGINT DEFAULT NULL,
length BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS POSITION (
 id INT NOT NULL AUTO_INCREMENT,
 vehicle_reg varchar(255) NOT NULL,
 country varchar(255) DEFAULT NULL,
 longitude BIGINT DEFAULT NULL,
 latitude BIGINT NOT NULL,
 timestamp TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS REPORT (

);

CREATE TABLE IF NOT EXISTS EVENT (

);

CREATE TABLE IF NOT EXISTS BORDERCROSS (

);