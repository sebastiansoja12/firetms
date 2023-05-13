package com.fire.truck;

import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.secondary.TruckRepository;
import com.fire.truck.domain.service.TruckService;
import com.fire.truck.domain.service.TruckServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckServiceTest {

    @Mock
    private TruckRepository truckRepository;

    private TruckService truckService;


    @BeforeEach
    void setup() {
        truckService = new TruckServiceImpl(truckRepository);
    }

    @Test
    void shouldAddTruck() {
        // given: create TruckRequest
        final TruckRequest truckRequest = new TruckRequest();
        truckRequest.setTelematicsEnabled(true);
        truckRequest.setVehicleReg("SR1234");


        // when
        truckService.addTruck(truckRequest);

        // then
        verify(truckRepository, times(1)).saveTruck(truckRequest);
    }

    @Test
    void shouldFindByPlate() {
        // given: create TruckRequest
        final TruckRequest truckRequest = new TruckRequest();
        truckRequest.setTelematicsEnabled(true);
        truckRequest.setVehicleReg("SR1234");


        // when
        truckService.getTruck(truckRequest.getVehicleReg());

        // then
        verify(truckRepository, times(1)).findByPlate(truckRequest.getVehicleReg());
    }
}
