package com.fire.report.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class TruckPositionMessage {

    private Instant raportTimeStamp;

    private Report report;
}
