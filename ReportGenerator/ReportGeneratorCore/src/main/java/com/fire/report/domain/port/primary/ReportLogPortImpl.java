package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.secondary.ReportRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportLogPortImpl implements ReportLogPort {

    private final ReportRepository reportRepository;

    @Override
    public void saveTruckPositionMessage(TruckPositionMessage message) {
        reportRepository.saveReport(message);
    }
}
