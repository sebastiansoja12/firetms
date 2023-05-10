package com.fire.report.domain.port.secondary;

import com.fire.report.domain.model.Report;

public interface ReportRepository {
    void saveReport(Report report);
}
