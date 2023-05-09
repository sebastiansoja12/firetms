package com.fire.report.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class EventDto {

    private Instant eventTimeStamp;

    private String countryOut;

    private String countryIn;
}
