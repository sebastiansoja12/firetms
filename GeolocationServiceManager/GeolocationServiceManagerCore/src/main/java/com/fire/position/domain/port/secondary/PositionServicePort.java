package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;

import java.util.List;

public interface PositionServicePort {

    void determineVehiclePosition(Truck truck, List<Position> positions);

    Position determineVehiclePositions(Truck truck);

    void createBorderCrossingEvent(Position position, Position newPosition);
}
