package com.fire.truck;

import com.fire.position.PositionService;
import com.fire.position.dto.CoordinateDto;
import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import com.fire.truck.domain.model.Position;
import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.port.secondary.TruckPositionServicePort;
import com.fire.truck.infrastructure.adapter.secondary.TruckPositionAdapter;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionRequestMapper;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionRequestMapperImpl;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionResponseMapper;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionResponseMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TruckPositionAdapterTest {


    @Mock
    private PositionService positionService;

    @Mock
    private TruckPositionServicePort truckPositionServicePort;

    private TruckPositionAdapter truckPositionAdapter;

    private static final String PLATE = "SR1234";


    @BeforeEach
    void setup() {
        final TruckPositionRequestMapper truckPositionRequestMapper = new TruckPositionRequestMapperImpl();
        final TruckPositionResponseMapper truckPositionResponseMapper = new TruckPositionResponseMapperImpl();
        truckPositionAdapter = new TruckPositionAdapter(positionService,
                truckPositionRequestMapper, truckPositionResponseMapper);
    }

    @Test
    void shouldDeterminePosition() {
        // given
        final TruckDto truckRequest = getTruckRequest();

        final List<PositionDto> expectedPositions = List.of(getPosition());

        final Truck truck = new Truck(PLATE);
        truck.setTelematicsEnabled(true);
        when(positionService.determineVehiclesPosition(truckRequest)).thenReturn(expectedPositions);
        // when
        final List<Position> actualPositions = truckPositionAdapter.determinePosition(truck);
        // then
        assertEquals(expectedPositions.size(), actualPositions.size());
    }


    private TruckDto getTruckRequest() {
        final TruckDto truckRequest = new TruckDto();
        truckRequest.setPlate(PLATE);
        truckRequest.setTelematicsEnabled(true);
        return truckRequest;
    }

    private PositionDto getPosition() {
        final PositionDto position = new PositionDto();
        position.setTimestamp(Instant.now().toString());
        position.setCountry("POL");
        position.setCoordinate(getCoordinates());
        position.setVehicleReg(PLATE);
        return position;
    }

    private CoordinateDto getCoordinates() {
        final CoordinateDto coordinate = new CoordinateDto();
        coordinate.setLatitude(19.0);
        coordinate.setLongitude(50.0);
        return coordinate;
    }
}
