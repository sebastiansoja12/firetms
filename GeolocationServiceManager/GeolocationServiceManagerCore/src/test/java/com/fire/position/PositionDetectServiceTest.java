package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.position.domain.service.PositionDetectService;
import com.fire.position.domain.service.PositionDetectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PositionDetectServiceTest {


    @Mock
    private PositionServicePort positionServicePort;

    private PositionDetectService positionDetectService;


    @BeforeEach
    void setup() {
        positionDetectService = new PositionDetectServiceImpl(positionServicePort);
    }

    @Test
    void shouldCreateBorderCrossingEvent() {
        // given
        final Position position = new Position();
        position.setVehicleReg("SR1234");
        position.setCoordinate(new Coordinate(10.0, 10.0));
        position.setTimestamp(Instant.now().toString());
        position.setCountry("POL");

        // create new position to check
        final Position newPosition = new Position();
        newPosition.setVehicleReg("SR1234");
        newPosition.setCoordinate(new Coordinate(20.0, 20.0));
        newPosition.setTimestamp(Instant.now().toString());
        newPosition.setCountry("DEU");

        // when
        positionDetectService.detectBorderCrossing(position, newPosition);

        // then
        verify(positionServicePort, times(1)).createBorderCrossingEvent(position, newPosition);
    }

    @Test
    void shouldNotCreateBorderCrossingEvent() {
        // given
        final Position position = new Position();
        position.setVehicleReg("SR1234");
        position.setCoordinate(new Coordinate(10.0, 10.0));
        position.setTimestamp(Instant.now().toString());
        position.setCountry("POL");

        // create new position to check
        final Position newPosition = new Position();
        newPosition.setVehicleReg("SR1234");
        newPosition.setCoordinate(new Coordinate(20.0, 20.0));
        newPosition.setTimestamp(Instant.now().toString());
        newPosition.setCountry("POL");

        // when
        positionDetectService.detectBorderCrossing(position, newPosition);

        // then
        // event was not sent
        verify(positionServicePort, times(0)).createBorderCrossingEvent(position, newPosition);
    }
}
