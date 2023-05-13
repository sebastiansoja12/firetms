package com.fire.report.infrastructure.adapter.primary.controller;

import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.primary.ReportControllerPort;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportControllerPort reportControllerPort;


    @GetMapping("/sorted/{vehicleReg}/{pageNumber}/{pageSize}")
    public ReportResponse getReportSorted(@PathVariable String vehicleReg,
                                          @PathVariable int pageNumber, @PathVariable int pageSize) {
        return reportControllerPort.findByVehicleReg(vehicleReg, pageNumber, pageSize);
    }

    @GetMapping("/sorted/{vehicleReg}/dateFrom/{dateFrom}/dateTo/{dateTo}")
    public ReportResponse getReportSorted(@PathVariable String vehicleReg,
                                          @PathVariable String dateFrom, @PathVariable String dateTo) {
        return reportControllerPort.generateReportByDates(vehicleReg, dateFrom, dateTo);
    }
}
