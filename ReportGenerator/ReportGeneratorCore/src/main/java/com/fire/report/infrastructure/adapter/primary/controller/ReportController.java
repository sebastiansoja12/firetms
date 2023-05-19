package com.fire.report.infrastructure.adapter.primary.controller;

import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.primary.ReportControllerPort;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
                                          @PathVariable @Valid int pageNumber,
                                          @PathVariable @Valid int pageSize) {
        return reportControllerPort.findByVehicleReg(vehicleReg, pageNumber, pageSize);
    }

    @GetMapping("/sorted/{vehicleReg}/dateFrom/{dateFrom}/dateTo/{dateTo}")
    public ReportResponse getReportSorted(@PathVariable String vehicleReg,
                                          @PathVariable @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String dateFrom,
                                          @PathVariable @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") String dateTo) {
        return reportControllerPort.generateReportByDates(vehicleReg, dateFrom, dateTo);
    }
}
