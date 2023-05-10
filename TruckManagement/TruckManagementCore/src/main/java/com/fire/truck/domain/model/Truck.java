package com.fire.truck.domain.model;

import lombok.Data;

@Data
public class Truck {

    private String plate;

    private boolean telematicsEnabled;

    public Truck(String plate) {
        this.plate = plate;
    }
}
