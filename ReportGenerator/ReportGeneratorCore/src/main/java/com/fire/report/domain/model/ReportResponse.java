package com.fire.report.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ReportResponse {

    private Instant raportTimeStamp;

    private Report report;

    public ReportResponse(Report report) {
        this.report = report;
        this.raportTimeStamp = Instant.now();
    }
}
