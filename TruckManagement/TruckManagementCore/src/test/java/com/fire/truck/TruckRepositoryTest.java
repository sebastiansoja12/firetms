package com.fire.truck;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.infrastructure.adapter.secondary.TruckReadRepository;
import com.fire.truck.infrastructure.adapter.secondary.TruckRepositoryImpl;
import com.fire.truck.infrastructure.adapter.secondary.entity.TruckEntity;
import com.fire.truck.infrastructure.adapter.secondary.exception.TruckNotFoundException;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckRepositoryTest {

    @Mock
    private TruckReadRepository truckReadRepository;

    @Mock
    private TruckModelMapper truckModelMapper;

    @InjectMocks
    private TruckRepositoryImpl truckRepositoryImpl;

    @Test
    void shouldFindTruckByPlate() {
        // given
        final String plate = "SR1234";
        final TruckEntity truckEntity = new TruckEntity();
        truckEntity.setPlate(plate);
        final Truck expectedTruck = new Truck(plate);
        expectedTruck.setPlate(plate);

        when(truckReadRepository.findByPlate(plate)).thenReturn(Optional.of(truckEntity));
        when(truckModelMapper.map(truckEntity)).thenReturn(expectedTruck);

        // when
        final Truck actualTruck = truckRepositoryImpl.findByPlate(plate);

        // then
        assertNotNull(actualTruck);
        assertEquals(expectedTruck.getPlate(), actualTruck.getPlate());
        verify(truckReadRepository, times(1)).findByPlate(plate);
        verify(truckModelMapper, times(1)).map(truckEntity);
    }

    @Test
    void shouldNotFindTruckByPlateAndThrowException() {
        // given
        final String plate = "SR1234";

        when(truckReadRepository.findByPlate(plate)).thenReturn(Optional.empty());

        // when
        final Executable executable = () -> truckRepositoryImpl.findByPlate(plate);

        // then
        final TruckNotFoundException truckNotFoundException = assertThrows(TruckNotFoundException.class, executable);
        assertThat(truckNotFoundException.getMessage())
                .isEqualTo("Truck with plate: " + plate + " was not found!");
    }

    @Test
    void shouldSaveTruck() {
        // given
        final TruckRequest truckRequest = new TruckRequest();
        final TruckEntity truckEntity = new TruckEntity();
        when(truckModelMapper.map(truckRequest)).thenReturn(truckEntity);

        // when
        truckRepositoryImpl.saveTruck(truckRequest);

        // then
        verify(truckReadRepository, times(1)).save(truckEntity);
    }
}
