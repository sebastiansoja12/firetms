package com.fire.truck;

import com.fire.truck.domain.model.Coordinate;
import com.fire.truck.domain.model.Position;
import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.primary.TruckPort;
import com.fire.truck.domain.port.primary.TruckPortImpl;
import com.fire.truck.domain.service.TruckService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckPortImplTest {

    @Mock
    private TruckService truckService;

    private TruckPort truckPort;

    @BeforeEach
    void setup() {
        truckPort = new TruckPortImpl(truckService);
    }

    @Test
    void shouldAddTruck() {
        // given: create TruckRequest
        final TruckRequest truckRequest = new TruckRequest();
        truckRequest.setTelematicsEnabled(true);
        truckRequest.setVehicleReg("SR1234");

        final List<TruckRequest> truckRequests = List.of(truckRequest);

        // when
        truckPort.addTruck(truckRequests);

        // then
        verify(truckService, times(1)).addTruck(truckRequest);
    }

    @Test
    void shouldNotAddTruckWhenRequestIsNull() {
        // given: empty request array list
        final List<TruckRequest> truckRequests = new ArrayList<>();

        // when
        truckPort.addTruck(truckRequests);

        // then
        verify(truckService, times(0)).addTruck(any());
    }


    @Test
    void shouldGetTruck() {
        // given
        final String plate = "SR1234";

        // create expected Truck
        final Truck expectedTruck = new Truck(plate);

        when(truckService.getTruck(plate)).thenReturn(expectedTruck);

        // when
        final Truck truck = truckPort.getTruckByPlate(plate);

        // then
        assertEquals(truck, truck);
    }

    private List<Position> createPositions() {
        final Position position = new Position();
        final Coordinate coordinate = createCoordinates();
        position.setCoordinate(coordinate);
        position.setCountry("POL");
        position.setTimestamp(Instant.now().toString());
        return List.of(position);
    }

    private Coordinate createCoordinates() {
        final Coordinate coordinate = new Coordinate();
        coordinate.setLongitude(50.0);
        coordinate.setLatitude(19.0);
        return coordinate;
    }
}
