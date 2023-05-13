CREATE TABLE IF NOT EXISTS TRUCK
(
    plate      varchar(255) NOT NULL,
    telematics BOOLEAN      NOT NULL
);

CREATE TABLE IF NOT EXISTS POSITION
(
    id          INT          NOT NULL AUTO_INCREMENT,
    vehicle_reg varchar(255) NOT NULL,
    country     varchar(255) DEFAULT NULL,
    longitude   DOUBLE       DEFAULT NULL,
    latitude    DOUBLE       NOT NULL,
    time_stamp  varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS EVENT
(
    id               BIGINT    NOT NULL AUTO_INCREMENT,
    event_time_stamp DATETIME NOT NULL,
    country_out      VARCHAR(255) NOT NULL,
    country_in       VARCHAR(255) NOT NULL,
    vehicle_reg      VARCHAR(255) NOT NULL
);
