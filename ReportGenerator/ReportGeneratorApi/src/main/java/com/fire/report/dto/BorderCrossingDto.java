package com.fire.report.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BorderCrossingDto {

    private String vehicleReg;

    private List<EventDto> event;
}
