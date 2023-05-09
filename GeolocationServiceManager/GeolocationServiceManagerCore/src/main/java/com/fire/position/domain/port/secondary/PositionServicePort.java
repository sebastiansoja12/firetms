package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;

public interface PositionServicePort {

    void determineVehiclePosition(Truck truck, Position position);
}
