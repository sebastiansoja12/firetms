package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.secondary.ReportRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportControllerPortImpl implements ReportControllerPort {

    private final ReportRepository reportRepository;

    @Override
    public ReportResponse downloadReportByVehiclePlate(String vehicleReg) {
        final Report report = reportRepository.findByVehicleReg(vehicleReg);
        return new ReportResponse(report);
    }
}
