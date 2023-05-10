package com.fire.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDto {
    private BorderCrossingDto borderCrossing;
}
