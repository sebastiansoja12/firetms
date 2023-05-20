package com.fire.position.domain.service;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionDetectServiceImpl implements PositionDetectService {

    private final PositionServicePort positionServicePort;

    @Override
    public void detectBorderCrossing(Position position, Position newPosition) {
        if (!position.getCountry().equals(newPosition.getCountry())) {
            positionServicePort.createBorderCrossingEvent(position, newPosition);
        }
    }
}
