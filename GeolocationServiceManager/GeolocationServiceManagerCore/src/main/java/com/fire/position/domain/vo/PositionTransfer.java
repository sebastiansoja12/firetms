package com.fire.position.domain.vo;

import lombok.Data;

@Data
public class PositionTransfer {
    private boolean telemetryEnabled;

    private Double longitude;

    private Double latitude;
}
