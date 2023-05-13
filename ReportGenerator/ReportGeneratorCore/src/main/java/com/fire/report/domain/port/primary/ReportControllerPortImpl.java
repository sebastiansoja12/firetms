package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.secondary.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

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
        final List<EventResponse> responses = eventRepository.findByVehicleReg(vehicleReg, pageable);
        final BorderCrossing borderCrossing = new BorderCrossing(vehicleReg, responses);
        return new ReportResponse(List.of(new Report(borderCrossing)));
    }

    @Override
    public ReportResponse generateReportByDates(String vehicleReg, String dateFrom, String dateTo) {
        final Instant dateF = Instant.parse(dateFrom);
        final Instant dateT = Instant.parse(dateTo);

        final List<EventResponse> responses = eventRepository.findByVehicleRegAndDates(vehicleReg, dateF, dateT);
        final BorderCrossing borderCrossing = new BorderCrossing(vehicleReg, responses);

        final Report report = new Report(borderCrossing);
        return new ReportResponse(List.of(report));
    }
}
