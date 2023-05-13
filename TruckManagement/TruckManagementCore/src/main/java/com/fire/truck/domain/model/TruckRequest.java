package com.fire.truck.domain.model;

import lombok.Data;

@Data
public class TruckRequest {

    private String vehicleReg;

    private boolean telematicsEnabled;

    public boolean isNotNull() {
        return !this.equals(EMPTY);
    }
    private static final TruckRequest EMPTY = new TruckRequest();
}
