package com.fire.updater.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Vehicle {

    private String vehicleReg;
    private boolean telemetryEnabled;
    private double latitude;
    private double longitude;

    private Instant timeStamp;
}
