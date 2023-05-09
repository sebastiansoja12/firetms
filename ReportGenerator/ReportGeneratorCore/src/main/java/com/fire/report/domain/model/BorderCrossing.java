package com.fire.report.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class BorderCrossing {

    private String vehicleReg;

    private List<Event> events;
}
