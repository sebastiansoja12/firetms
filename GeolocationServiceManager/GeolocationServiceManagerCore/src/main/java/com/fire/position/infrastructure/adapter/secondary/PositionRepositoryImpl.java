package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.infrastructure.adapter.secondary.exception.PositionNotFoundException;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final PositionReadRepository positionReadRepository;

    private final PositionModelMapper positionModelMapper;

    @Override
    public Position findPositionByPlate(String plate) {
        return positionReadRepository.findByVehicleReg(plate).map(positionModelMapper::map).orElseThrow(
        () -> new PositionNotFoundException("Position with plate: " + plate + " was not found!"));
    }
}
