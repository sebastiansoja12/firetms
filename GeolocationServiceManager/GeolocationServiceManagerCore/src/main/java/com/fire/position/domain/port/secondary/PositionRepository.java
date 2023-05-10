package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;

import java.util.List;

public interface PositionRepository {
    List<Position> findPositionsByPlate(String plate);

    Position findPositionByPlate(String plate);

    List<Position> save(Position positions);
}
