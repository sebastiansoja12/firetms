package com.fire.report;

import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.domain.port.primary.ReportControllerPort;
import com.fire.report.domain.port.primary.ReportControllerPortImpl;
import com.fire.report.domain.port.secondary.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerLogPortTest {

    @Mock
    private EventRepository eventRepository;

    private ReportControllerPort reportControllerPort;

    @BeforeEach
    void setup() {
        reportControllerPort = new ReportControllerPortImpl(eventRepository);
    }

    @Test
    void shouldFindByVehicleReg() {
        // given

        // pageable values
        final int pageNumber = 1;
        final int pageSize = 1;

        // plate of vehicle
        final String vehicleReg = "SR1234";

        // create event responses
        final List<EventResponse> eventResponses = createEventResponseList();

        // create pageable
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        when(eventRepository.findByVehicleReg(vehicleReg, pageable)).thenReturn(eventResponses);
        // when
        final ReportResponse response =
                reportControllerPort.findByVehicleReg(vehicleReg, pageNumber, pageSize);

        // then
        assertThat(response.getReport().size()).isEqualTo(1);
        assertThat(response.getReport().get(0).getBorderCrossingEvent().getEvents()
                .size()).isEqualTo(1);
    }

    @Test
    void shouldGenerateByDates() {
        // given
        final String dateFrom = "2023-05-13T22:01:53.074359500Z";
        final String dateTo = "2023-05-13T22:01:53.074359500Z";
        final String vehicleReg = "SR1234";


        final List<EventResponse> eventResponses = createEventResponseList();

        when(eventRepository.findByVehicleRegAndDates(any(), any(), any())).thenReturn(eventResponses);
        // when
        final ReportResponse response =
                reportControllerPort.generateReportByDates(vehicleReg, dateFrom, dateTo);
        // then
        assertThat(response.getReport().get(0).getBorderCrossingEvent().getEvents()).isNotEmpty();
    }

    private List<EventResponse> createEventResponseList() {
        final EventResponse eventResponse = new EventResponse();
        eventResponse.setEventTimeStamp(Instant.now());
        eventResponse.setCountryIn("POL");
        eventResponse.setCountryOut("DEU");
        return List.of(eventResponse);
    }
}
