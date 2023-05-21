package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.PositionResponse;
import com.fire.position.domain.model.TruckPosition;
import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.domain.port.primary.PositionPortImpl;
import com.fire.position.domain.port.secondary.*;
import com.fire.position.domain.service.PositionDetectService;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PositionPortTest {


    private PositionPort positionPort;

    @Mock
    private PositionDetectService positionDetectService;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private CalculateDistanceService calculateDistanceService;

    @Mock
    private InterpolationService interpolationService;


    @BeforeEach
    void setup() {
        positionPort = new PositionPortImpl(positionDetectService, positionRepository, calculateDistanceService,
                interpolationService);
    }

    @Test
    void shouldInsertPosition() {
        // given
        final Position position = buildPosition();
        when(positionRepository.savePosition(position)).thenReturn(any());
        // when
        positionPort.insertPosition(position);

        // then
        verify(positionRepository, times(1)).savePosition(position);
    }

    @Test
    void shouldDetermineNewestPosition() {
        // given
        final String plate = "SR1234";

        // build new position of a vehicle
        final Position newPosition = buildPosition();

        // build to find previous position of a vehicle
        final Optional<PositionEntity> positionEntity = buildPositionEntity();

        when(positionRepository.findPositionOnPlate(plate)).thenReturn(positionEntity);
        // when
        positionPort.determineNewestPosition(List.of(newPosition));

        // then
        verify(positionRepository, times(1)).findPositionOnPlate(plate);
        verify(positionDetectService, times(1)).detectBorderCrossing(
                any(), any()
        );
        verify(positionRepository, times(1)).savePosition(any());
    }

    @Test
    void shouldDeterminePositionUsingInterpolation() {
        // given
        final String plate = "SR1234";

        // build new position of a vehicle
        final Position position = new Position();
        position.setCountry("DEU");
        position.setCoordinate(new Coordinate(40.3256188, 18.4281651));
        position.setTimestamp(Instant.now().toString());
        position.setVehicleReg("SR1234");

        // build to find previous position of a vehicle
        final Optional<PositionEntity> positionEntity = buildPositionEntity();

        final Position newPosition = positionEntity.map(this::map).orElse(position);

        when(positionRepository.findPositionOnPlate(plate)).thenReturn(positionEntity);
        // when
        positionPort.determineNewestPosition(List.of(position));

        // then
        verify(positionRepository, times(1)).findPositionOnPlate(plate);
        verify(positionDetectService, times(1)).detectBorderCrossing(any(), any());
    }

    @Test
    void shouldNotDetermineNewestPositionWhenThereIsNoPreviousPosition() {
        // given
        final String plate = "SR1234";
        final Position position = new Position();
        position.setCountry("POL");
        position.setCoordinate(new Coordinate(50.0, 20.0));
        position.setTimestamp(Instant.now().toString());
        position.setVehicleReg("SR1234");

        when(positionRepository.findPositionOnPlate(plate)).thenReturn(Optional.empty());
        // when
        positionPort.determineNewestPosition(List.of(position));

        // then
        verify(positionRepository, times(1)).findPositionOnPlate(plate);
        verify(positionDetectService, times(1)).detectBorderCrossing(
                any(), any()
        );
        verify(positionRepository, times(1)).savePosition(position);
    }

    @Test
    void shouldGetVehiclePosition() {
        // given
        final String plate = "SR1234";
        final List<Position> positionEntity = List.of(buildPositionWithoutAddingTime());
        final List<PositionResponse> positions = buildPositionsList();
        final int pageNumber = 1;
        final int pageSize = 1;

        // build expected truck position
        final TruckPosition expectedTruckPosition = new TruckPosition(plate, positions);

        doReturn(positionEntity)
                .when(positionRepository)
                .findPositionsByPlate(plate, pageNumber, pageNumber);
        // when
        final TruckPosition actualTruckPosition = positionPort.getVehiclePosition(plate, pageNumber, pageSize);

        // then
        verify(positionRepository, times(1)).findPositionsByPlate(any(), anyInt(), anyInt());
        assertEquals(expectedTruckPosition, actualTruckPosition);
    }

    private Position map(PositionEntity positionEntity) {
        return new Position(positionEntity.getVehicleReg(),
                new Coordinate(positionEntity.getLongitude(), positionEntity.getLatitude()),
                positionEntity.getCountry(), positionEntity.getTimestamp().toString());
    }

    private static List<PositionResponse> buildPositionsList() {
        final PositionResponse positionResponse = new PositionResponse();
        positionResponse.setCoordinate(new Coordinate(50.0, 25.0));
        positionResponse.setTimestamp("2023-05-21T19:39:27.793359800Z");
        positionResponse.setCountry("DEU");
        return List.of(positionResponse);
    }

    private static Position buildPosition() {
        final Position position = new Position();
        position.setCountry("DEU");
        position.setCoordinate(new Coordinate(50.0, 25.0));
        position.setTimestamp(String.valueOf(Instant.now().plus(2L, ChronoUnit.HOURS)));
        position.setVehicleReg("SR1234");
        return position;
    }

    private static Position buildPositionWithoutAddingTime() {
        final Position position = new Position();
        position.setCountry("DEU");
        position.setCoordinate(new Coordinate(50.0, 25.0));
        position.setTimestamp("2023-05-21T19:39:27.793359800Z");
        position.setVehicleReg("SR1234");
        return position;
    }

    private static Optional<PositionEntity> buildPositionEntity() {
        final PositionEntity position = new PositionEntity();
        position.setCountry("POL");
        position.setTimestamp(Instant.now());
        position.setLongitude(52.3256188);
        position.setLatitude(14.4281651);
        position.setVehicleReg("SR1234");
        return Optional.of(position);
    }
}
