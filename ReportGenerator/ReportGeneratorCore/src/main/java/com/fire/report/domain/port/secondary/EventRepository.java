package com.fire.report.domain.port.secondary;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.model.ReportResponse;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface EventRepository {
    void save(Event event);

    void save(List<Event> events);

    List<EventResponse> findByVehicleReg(String vehicleReg, Pageable pageable);

    List<EventResponse> findByVehicleRegAndDates(String vehicleReg, Instant dateFrom, Instant dateTo);
}
