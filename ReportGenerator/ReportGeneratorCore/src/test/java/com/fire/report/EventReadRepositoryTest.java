package com.fire.report;

import com.fire.report.infrastructure.adapter.secondary.EventReadRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = ReportTestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventReadRepositoryTest {

    @Autowired
    private EventReadRepository eventReadRepository;

    @Test
    @DatabaseSetup("/events.xml")
    void shouldFindEventByVehicleReg() {
        // given
        final String vehicleReg = "SR1234";
        final int pageNumber = 0;
        final int pageSize = 1;
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // when
        final Page<EventEntity> event =
                eventReadRepository.findDistinctEventTimestampByVehicleReg(vehicleReg, pageable);
        // then
        assertFalse(event.isEmpty());
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
        final Page<EventEntity> event =
                eventReadRepository.findDistinctEventTimestampByVehicleReg(vehicleReg, pageable);
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
        final List<EventEntity> event =
                eventReadRepository.findEventsByVehicleRegAndDates(vehicleReg, dateF, dateT);
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
        final List<EventEntity> event =
                eventReadRepository.findEventsByVehicleRegAndDates(vehicleReg, dateF, dateT);
        // then
        assertTrue(event.isEmpty());
    }

    @Test
    @DatabaseSetup("/events.xml")
    void shouldNotFindEventByVehicleRegAndDatesWhenDatesAreWrong() {
        // given
        final String vehicleReg = "SR1234";
        final String dateFrom = "2023-05-10T08:12:34Z";
        final String dateTo = "2023-05-11T08:12:34Z";

        final Instant dateF = Instant.parse(dateFrom);
        final Instant dateT = Instant.parse(dateTo);

        // when
        final List<EventEntity> event =
                eventReadRepository.findEventsByVehicleRegAndDates(vehicleReg, dateF, dateT);
        // then
        assertTrue(event.isEmpty());
    }
}
