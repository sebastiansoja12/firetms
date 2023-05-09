package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Truck;

public interface PositionPort {

    void determineVehiclePosition(Truck truck);
}
