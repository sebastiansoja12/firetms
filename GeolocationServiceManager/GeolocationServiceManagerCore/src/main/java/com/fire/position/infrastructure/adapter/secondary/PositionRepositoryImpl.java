package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import com.fire.position.infrastructure.adapter.secondary.exception.PositionNotFoundException;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {

    private final PositionReadRepository positionReadRepository;

    private final PositionModelMapper positionModelMapper;

    @Override
    public List<Position> findPositionsByPlate(String plate) {
        final Sort sort = Sort.by(Sort.Direction.DESC, "id");

        return positionReadRepository.findAllByVehicleReg(plate, sort)
                .stream().map(positionModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Position findPositionByPlate(String plate) {
        final Sort sort = Sort.by(Sort.Direction.DESC, "timestamp");
        final Position position = positionReadRepository.findAllByVehicleReg(plate, sort).stream().map(positionModelMapper::map)
                .findFirst()
                .orElseThrow(() -> new PositionNotFoundException("Position with plate: " + plate + " was not found!"));

        return position;
    }

    @Override
    public List<Position> save(Position position) {
        final PositionEntity positionEntities = positionModelMapper.map(position);
        positionReadRepository.save(positionEntities);
        final Sort sort = Sort.by(Sort.Direction.DESC, "id");

        return positionReadRepository.findAllByVehicleReg(position.getVehicleReg(), sort).stream()
                .map(positionModelMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void savePosition(Position position) {
        final PositionEntity positionEntity = positionModelMapper.map(position);
        positionReadRepository.save(positionEntity);
    }

    @Override
    public boolean checkIfExistsAlreadyPreviousPosition(Position position) {
        return positionReadRepository.existsByVehicleReg(position.getVehicleReg());
    }
}
