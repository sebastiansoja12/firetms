package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;

public interface PositionRepository {
    Position findPositionByPlate(String plate);
}
