package com.fire.report;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.primary.ReportLogPort;
import com.fire.report.domain.port.primary.ReportLogPortImpl;
import com.fire.report.domain.port.secondary.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReportLogTest {

    @Mock
    private EventRepository eventRepository;

    private ReportLogPort reportLogPort;


    @BeforeEach
    void setup() {
        reportLogPort = new ReportLogPortImpl(eventRepository);
    }

    @Test
    void shouldSaveTruckBorderCrossingInformation() {
        // given
        final Event event = getEvent();
        final TruckPositionMessage truckPositionMessage = new TruckPositionMessage();
        final List<Event> events = List.of(event);
        truckPositionMessage.setEvents(events);
        // when
        reportLogPort.saveTruckBorderCrossingInformation(truckPositionMessage);
        // then
        verify(eventRepository, times(1)).save(events);
    }

    private static Event getEvent() {
        final Event event = new Event();
        event.setCountryIn("POL");
        event.setCountryOut("DEU");
        event.setEventTimeStamp(Instant.now());
        event.setVehicleReg("SR1234");
        return event;
    }
}
