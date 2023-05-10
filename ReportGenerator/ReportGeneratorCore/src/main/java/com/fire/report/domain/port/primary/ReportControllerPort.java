package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.ReportResponse;

public interface ReportControllerPort {
    ReportResponse downloadReportByVehiclePlate(String vehicleReg);
}
