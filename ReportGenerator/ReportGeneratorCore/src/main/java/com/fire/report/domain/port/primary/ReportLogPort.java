package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.TruckPositionMessage;

public interface ReportLogPort {
    void saveTruckPositionMessage(TruckPositionMessage message);
}
