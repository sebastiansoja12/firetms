package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;

public interface PositionServicePort {

    void createBorderCrossingEvent(Position position, Position newPosition);
}
