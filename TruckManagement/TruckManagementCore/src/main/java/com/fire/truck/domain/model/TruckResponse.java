package com.fire.truck.domain.model;

import lombok.Data;

@Data
public class TruckResponse {

    private String vehicleReg;

    private boolean telematicsEnabled;
}
