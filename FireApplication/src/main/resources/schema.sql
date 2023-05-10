CREATE TABLE IF NOT EXISTS TRUCK (
plate varchar(255) NOT NULL,
telematics BOOLEAN not null
);

CREATE TABLE IF NOT EXISTS POSITION (
 id INT NOT NULL AUTO_INCREMENT,
 vehicle_reg varchar(255) NOT NULL,
 country varchar(255) DEFAULT NULL,
 longitude DOUBLE DEFAULT NULL,
 latitude DOUBLE NOT NULL,
 time_stamp varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS BORDER_CROSS (
vehicle_reg varchar(255) not null,
primary key (vehicle_reg)
);

CREATE TABLE IF NOT EXISTS REPORT (
id varchar(255) not null,
border_cross_vehicle_reg varchar(255) not null,
primary key (border_cross_vehicle_reg),
foreign key (border_cross_vehicle_reg) references BORDER_CROSS (vehicle_reg)
);

CREATE TABLE IF NOT EXISTS EVENT (
id bigint not null auto_increment,
event_time_stamp time not null,
country_out varchar(255),
country_in varchar(255),
border_cross_vehicle_reg varchar(255) not null,
foreign key (border_cross_vehicle_reg) references BORDER_CROSS (vehicle_reg)
);
