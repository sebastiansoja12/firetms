package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.port.secondary.PositionServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionServicePort positionServicePort;

    private final PositionRepository positionRepository;

    @Override
    public void determineVehiclePosition(Truck truck) {
        final Position position = positionRepository.findPositionByPlate(truck.getPlate());
        positionServicePort.determineVehiclePosition(truck, position);
    }
}
