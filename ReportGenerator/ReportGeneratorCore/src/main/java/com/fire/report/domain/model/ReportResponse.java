package com.fire.report.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ReportResponse {

    private Instant timeStamp;

    private Report report;

    public ReportResponse(Report report) {
        this.report = report;
        this.timeStamp = Instant.now();
    }
}
