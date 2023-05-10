CREATE TABLE IF NOT EXISTS TRUCK
(
    plate      varchar(255) NOT NULL,
    telematics BOOLEAN      not null
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
    event_time_stamp TIMESTAMP NOT NULL,
    country_out      VARCHAR(255),
    country_in       VARCHAR(255),
    border_cross_id varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS BORDER_CROSS
(
    id          VARCHAR(255) NOT NULL,
    vehicle_reg VARCHAR(255) NOT NULL,
    events_id      BIGINT,
    border_cross_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (events_id) REFERENCES EVENT (id)
);


CREATE TABLE IF NOT EXISTS REPORT
(
    id varchar(255) not null,
    border_cross_id varchar(255) not null,
    primary key (id),
    foreign key (border_cross_id) references BORDER_CROSS (id)
);
