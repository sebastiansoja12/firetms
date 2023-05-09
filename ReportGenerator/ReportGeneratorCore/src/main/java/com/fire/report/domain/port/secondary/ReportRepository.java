package com.fire.report.domain.port.secondary;

import com.fire.report.domain.model.TruckPositionMessage;

public interface ReportRepository {
    void saveReport(TruckPositionMessage truckPositionMessage);
}
