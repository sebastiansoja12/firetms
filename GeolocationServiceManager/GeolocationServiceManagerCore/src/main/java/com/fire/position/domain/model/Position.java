package com.fire.position.domain.model;

import lombok.Data;

@Data
public class Position {

    private String vehicleReg;

    private Coordinate coordinate;
    private String country;
    private String timestamp;

}