package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.fire.position.infrastructure.adapter.secondary.exception.PositionNotFoundException;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final PositionReadRepository positionReadRepository;

    private final PositionModelMapper positionModelMapper;

    @Override
    public List<Position> findPositionsByPlate(String plate) {
        return positionReadRepository.findAllByVehicleReg(plate)
                .stream().map(positionModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Position findPositionByPlate(String plate) {
        return positionReadRepository.findByVehicleReg(plate).map(positionModelMapper::map).stream()
                .findFirst()
                .orElseThrow(() -> new PositionNotFoundException("Position with plate: " + plate + " was not found!"));
    }

    @Override
    public List<Position> save(Position position) {
        final PositionEntity positionEntities = positionModelMapper.map(position);
        positionReadRepository.save(positionEntities);

        // Map the entities to model objects and return
        return positionReadRepository.findAllByVehicleReg(position.getVehicleReg()).stream()
                .map(positionModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void savePosition(Position position) {
        final PositionEntity positionEntity = positionModelMapper.map(position);
        positionReadRepository.save(positionEntity);
    }
}
