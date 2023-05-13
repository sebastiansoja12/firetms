package com.fire.position.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TruckPosition {
    private String vehicleReg;

    private List<PositionResponse> positions;
}
