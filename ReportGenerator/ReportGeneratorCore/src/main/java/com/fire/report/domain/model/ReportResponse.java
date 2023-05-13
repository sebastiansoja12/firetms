package com.fire.report.domain.model;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ReportResponse {

    private Instant raportTimeStamp;

    private List<Report> report;

    public ReportResponse(List<Report> report) {
        this.report = report;
        this.raportTimeStamp = Instant.now();
    }
}
