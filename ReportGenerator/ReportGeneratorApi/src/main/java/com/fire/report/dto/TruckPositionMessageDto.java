package com.fire.report.dto;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class TruckPositionMessageDto {

    private Instant raportTimeStamp;

    private List<EventDto> events;

}
