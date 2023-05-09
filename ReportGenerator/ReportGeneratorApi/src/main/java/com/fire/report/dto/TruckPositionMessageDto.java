package com.fire.report.dto;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TruckPositionMessageDto {

    private Instant raportTimeStamp;

    private ReportDto report;

}
