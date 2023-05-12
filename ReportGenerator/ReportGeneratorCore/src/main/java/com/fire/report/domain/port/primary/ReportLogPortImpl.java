package com.fire.report.domain.port.primary;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.secondary.EventRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReportLogPortImpl implements ReportLogPort {

    private final EventRepository eventRepository;

    @Override
    public void saveTruckBorderCrossingInformation(TruckPositionMessage message) {
        final List<Event> events = message.getEvents();
        eventRepository.save(events);
    }
}
