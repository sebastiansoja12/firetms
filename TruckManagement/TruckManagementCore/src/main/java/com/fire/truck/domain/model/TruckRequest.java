package com.fire.truck.domain.model;

import lombok.Data;

@Data
public class TruckRequest {

    private String vehicleReg;

    private boolean telematicsEnabled;
}
