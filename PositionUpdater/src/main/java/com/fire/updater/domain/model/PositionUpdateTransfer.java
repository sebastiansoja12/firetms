package com.fire.updater.domain.model;

import lombok.Data;

@Data
public class PositionUpdateTransfer {

    private String vehicleReg;
    private boolean telemetryEnabled;
    private Double longitude;
    private Double latitude;

    private String timeStamp;
}
