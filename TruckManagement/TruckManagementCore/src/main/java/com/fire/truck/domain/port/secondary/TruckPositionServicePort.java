package com.fire.truck.domain.port.secondary;

import com.fire.truck.domain.model.Position;
import com.fire.truck.domain.model.Truck;

import java.util.List;

public interface TruckPositionServicePort {

    List<Position> determinePosition(Truck truck);

    void determinePositionWithReport(Truck truck);

}
