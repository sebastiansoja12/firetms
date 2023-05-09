package com.fire.report.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class Event {

    private Instant eventTimeStamp;

    private String countryOut;

    private String countryIn;
}
