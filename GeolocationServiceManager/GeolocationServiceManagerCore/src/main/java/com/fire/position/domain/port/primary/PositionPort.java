package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.model.TruckPosition;

import java.util.List;

public interface PositionPort {

    void insertPosition(Position position);

    void determineNewestPosition(List<Position> positions);

    TruckPosition getVehiclePosition(String vehicleReg, int pageNumber, int pageSize);
}
