package com.fire.report.domain.port.primary;

import com.fire.report.domain.exception.EventMissingException;
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
import java.util.Objects;

@AllArgsConstructor
public class ReportLogPortImpl implements ReportLogPort {

    private final ReportRepository reportRepository;

    private final BorderCrossRepository borderCrossRepository;

    private final EventRepository eventRepository;

    @Override
    public void saveTruckBorderCrossingInformation(TruckPositionMessage message) {
        final List<Event> events = message.getEvents();
        eventRepository.save(events);
    }
}
