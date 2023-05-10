package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;

import java.util.List;

public interface PositionPort {

    List<Position> determineVehiclePosition(Truck truck);

    void determineVehiclePositionWithReport(Truck truck);
}
