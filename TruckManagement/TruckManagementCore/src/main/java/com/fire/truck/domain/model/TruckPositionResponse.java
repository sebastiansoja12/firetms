package com.fire.truck.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TruckPositionResponse {
    private String vehicleReg;

    private List<Position> positions;
}
