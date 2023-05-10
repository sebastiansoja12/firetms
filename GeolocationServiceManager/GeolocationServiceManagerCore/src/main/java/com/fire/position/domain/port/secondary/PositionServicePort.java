package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;

import java.util.List;

public interface PositionServicePort {

    void determineVehiclePosition(Truck truck, Position position);

    Position determineVehiclePositions(Truck truck);
}
