package com.fire.truck.dto;

import lombok.Data;

@Data
public class TruckRequestDto {
    private String vehicleReg;

    private boolean telemetryEnabled;
}
