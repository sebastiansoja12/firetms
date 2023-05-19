package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.PositionRequest;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;

import java.util.List;
import java.util.Optional;

public interface PositionRepository {
    List<Position> findPositionsByPlate(String plate, int pageSize, int pageNumber);

    Position savePosition(Position position);

    Optional<PositionEntity> findPositionOnPlate(String plate);

}
