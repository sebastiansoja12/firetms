package com.fire.position.domain.service;

import com.fire.position.domain.model.Position;

public interface PositionDetectService {
    void detectBorderCrossing(Position position, Position newPosition);
}
