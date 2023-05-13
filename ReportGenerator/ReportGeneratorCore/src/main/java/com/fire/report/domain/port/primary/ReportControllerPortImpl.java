package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.secondary.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

@AllArgsConstructor
public class ReportControllerPortImpl implements ReportControllerPort {

    private final EventRepository eventRepository;

    @Override
    public ReportResponse downloadReportByVehiclePlate(String vehicleReg) {

        return new ReportResponse(null);
    }

    @Override
    public ReportResponse findByVehicleReg(String vehicleReg, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return eventRepository.findByVehicleReg(vehicleReg, pageable);
    }

    @Override
    public ReportResponse generateReportByDates(String vehicleReg, String dateFrom, String dateTo) {
        final Instant dateF = Instant.parse(dateFrom);
        final Instant dateT = Instant.parse(dateTo);
        return eventRepository.findByVehicleRegAndDates(vehicleReg, dateF, dateT);
    }
}
