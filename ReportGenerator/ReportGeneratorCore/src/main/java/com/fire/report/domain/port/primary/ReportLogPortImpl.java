package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.secondary.BorderCrossRepository;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.domain.port.secondary.ReportRepository;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ReportLogPortImpl implements ReportLogPort {

    private final ReportRepository reportRepository;

    private final BorderCrossRepository borderCrossRepository;

    private final EventRepository eventRepository;

    @Override
    public void saveTruckPositionMessage(TruckPositionMessage message) {
        final Report report = extractReportFromMessage(message);
        reportRepository.saveReport(report);
    }

    private Report extractReportFromMessage(TruckPositionMessage message) {
        return message.getReport();
    }
}
