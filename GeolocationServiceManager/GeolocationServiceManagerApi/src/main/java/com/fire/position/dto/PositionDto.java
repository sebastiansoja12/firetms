package com.fire.position.dto;

import lombok.Data;

@Data
public class PositionDto {

    private String vehicleReg;
    private CoordinateDto coordinate;
    private String country;
    private String timestamp;
}
