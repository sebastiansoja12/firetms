package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;

public interface InterpolationService {
    Position interpolatePosition(Position currentPosition, Position previousPosition);
}
