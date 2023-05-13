package com.fire.report;

import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.port.secondary.EventRepository;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = ReportTestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryImplTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DatabaseSetup("/events.xml")
    void shouldFindEventByVehicleReg() {
        // given
        final String vehicleReg = "SR1234";
        final int pageNumber = 0;
        final int pageSize = 1;
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // when
        final List<EventResponse> events =
                eventRepository.findByVehicleReg(vehicleReg, pageable);
        // then
        assertFalse(events.isEmpty());
    }

    @Test
    @DatabaseSetup("/events.xml")
    void shouldNotFindEventByVehicleReg() {
        // given
        final String vehicleReg = "abc";
        final int pageNumber = 0;
        final int pageSize = 1;
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // when
        final List<EventResponse> event =
                eventRepository.findByVehicleReg(vehicleReg, pageable);
        // then
        assertTrue(event.isEmpty());
    }

    @Test
    @DatabaseSetup("/events.xml")
    void shouldFindEventByVehicleRegAndDates() {
        // given
        final String vehicleReg = "SR1234";
        final String dateFrom = "2023-05-13T08:12:34Z";
        final String dateTo = "2023-05-14T08:12:34Z";

        final Instant dateF = Instant.parse(dateFrom);
        final Instant dateT = Instant.parse(dateTo);

        // when
        final List<EventResponse> event =
                eventRepository.findByVehicleRegAndDates(vehicleReg, dateF, dateT);
        // then
        assertFalse(event.isEmpty());
    }

    @Test
    @DatabaseSetup("/events.xml")
    void shouldNotFindEventByVehicleRegAndDatesWhenVehicleRegIsWrong() {
        // given
        final String vehicleReg = "abc";
        final String dateFrom = "2023-05-13T08:12:34Z";
        final String dateTo = "2023-05-14T08:12:34Z";

        final Instant dateF = Instant.parse(dateFrom);
        final Instant dateT = Instant.parse(dateTo);

        // when
        final List<EventResponse> event =
                eventRepository.findByVehicleRegAndDates(vehicleReg, dateF, dateT);
        // then
        assertTrue(event.isEmpty());
    }
}
