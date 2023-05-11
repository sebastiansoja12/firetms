package com.fire.report.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BorderCrossing {

    private String vehicleReg;

    private List<EventResponse> events;
}
