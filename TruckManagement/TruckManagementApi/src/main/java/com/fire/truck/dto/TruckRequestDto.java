package com.fire.truck.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TruckRequestDto {

    @Pattern(regexp = "[A-Z0-9]{6}")
    private String vehicleReg;

    private boolean telemetryEnabled;
}
