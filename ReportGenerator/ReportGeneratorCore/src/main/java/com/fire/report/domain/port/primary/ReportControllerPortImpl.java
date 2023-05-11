package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.domain.port.secondary.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class ReportControllerPortImpl implements ReportControllerPort {

    private final ReportRepository reportRepository;

    private final EventRepository eventRepository;

    @Override
    public ReportResponse downloadReportByVehiclePlate(String vehicleReg) {
        final Report report = reportRepository.findByVehicleReg(vehicleReg);
        return new ReportResponse(report);
    }

    @Override
    public ReportResponse findByVehicleReg(String vehicleReg, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return eventRepository.findByVehicleReg(vehicleReg, pageable);
    }
}
