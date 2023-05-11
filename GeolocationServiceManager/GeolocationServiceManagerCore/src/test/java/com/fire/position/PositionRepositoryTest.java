package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.infrastructure.adapter.secondary.PositionReadRepository;
import com.fire.position.infrastructure.adapter.secondary.PositionRepositoryImpl;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.fire.position.infrastructure.adapter.secondary.exception.PositionNotFoundException;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PositionRepositoryTest {

    @Mock
    private PositionReadRepository positionReadRepository;

    @Mock
    private PositionModelMapper positionModelMapper;

    @InjectMocks
    private PositionRepositoryImpl positionRepository;

    @Test
    void shouldReturnListOfPositions() {
        // given
        final String plate = "ABC123";
        final PositionEntity entity = new PositionEntity();
        entity.setVehicleReg(plate);
        final List<PositionEntity> entities = Collections.singletonList(entity);
        final Position position = new Position();
        when(positionReadRepository.findAllByVehicleReg(plate)).thenReturn(entities);
        when(positionModelMapper.map(entity)).thenReturn(position);

        // when
        final List<Position> result = positionRepository.findPositionsByPlate(plate);

        // then
        Assertions.assertThat(result).containsOnly(position);
        verify(positionReadRepository).findAllByVehicleReg(plate);
        verify(positionModelMapper).map(entity);
    }

    @Test
    void shouldFindPositionByVehiclePlate() {
        // given
        final String plate = "ABC123";
        final PositionEntity entity = new PositionEntity();
        entity.setVehicleReg(plate);
        final Position position = new Position();
        when(positionReadRepository.findByVehicleReg(plate)).thenReturn(Optional.of(entity));
        when(positionModelMapper.map(entity)).thenReturn(position);

        // when
        final Position result = positionRepository.findPositionByPlate(plate);

        // then
        Assertions.assertThat(result).isEqualTo(position);
        verify(positionReadRepository).findByVehicleReg(plate);
        verify(positionModelMapper).map(entity);
    }

    @Test
    void shouldNotFindByPlateAndThrowException() {
        // given
        final String plate = "ABC123";

        // when&&then
        Assertions.assertThatThrownBy(() -> positionRepository.findPositionByPlate(plate))
                .isInstanceOf(PositionNotFoundException.class)
                .hasMessage("Position with plate: ABC123 was not found!");
    }


    @Test
    void shouldSavePosition() {
        // given
        final String plate = "ABC123";
        final Position position = new Position();
        position.setVehicleReg(plate);
        final PositionEntity entity = new PositionEntity();
        entity.setVehicleReg(plate);
        when(positionModelMapper.map(position)).thenReturn(entity);

        // when
        positionRepository.savePosition(position);

        // then
        verify(positionModelMapper).map(position);
        verify(positionReadRepository).save(entity);
    }

    private Coordinate coordinates() {
        return Coordinate.builder()
                .longitude(50.01221)
                .latitude(19.122121)
                .build();
    }
}
