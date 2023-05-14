package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.infrastructure.adapter.secondary.PositionAdapter;
import com.fire.report.LogEventPublisher;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.TruckPositionMessageDto;
import com.fire.report.event.TruckPositionDetermineEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PositionServicePortTest {

    private PositionAdapter positionAdapter;

    @Mock
    private LogEventPublisher logEventPublisher;

    @BeforeEach
    void setup() {
        positionAdapter = new PositionAdapter(logEventPublisher);
    }

    @Test
    void shouldCreateBorderCrossingEvent() {
        // given

        // build previous and next position
        final Position previousPositon = buildPreviousPosition();
        final Position newPositon = buildNewPosition();

        // build instant
        final Instant instant = Instant.now();

        // build event informations
        final EventDto eventInformation = EventDto.builder()
                .eventTimeStamp(instant)
                .countryIn(newPositon.getCountry())
                .countryOut(previousPositon.getCountry())
                .vehicleReg(previousPositon.getVehicleReg())
                .build();

        // build message to be sent
        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                .raportTimeStamp(instant)
                .events(List.of(eventInformation))
                .build();

        final TruckPositionDetermineEvent event = buildEvent(message);


        // when
        positionAdapter.createBorderCrossingEvent(previousPositon, newPositon);


        // then
        verify(logEventPublisher, times(1)).send(any());
    }

    private TruckPositionDetermineEvent buildEvent(TruckPositionMessageDto message) {
        return TruckPositionDetermineEvent.builder()
                .truckPositionMessage(message)
                .build();
    }

    private static Position buildPreviousPosition() {
        final Position position = new Position();
        position.setCountry("POL");
        position.setCoordinate(new Coordinate(51.0, 25.0));
        position.setTimestamp("2022-05-13 21:30:00");
        position.setVehicleReg("SR1234");
        return position;
    }

    private static Position buildNewPosition() {
        final Position position = new Position();
        position.setCountry("DEU");
        position.setCoordinate(new Coordinate(52.0, 25.0));
        position.setTimestamp("2022-05-14 21:30:00");
        position.setVehicleReg("SR1234");
        return position;
    }


}
