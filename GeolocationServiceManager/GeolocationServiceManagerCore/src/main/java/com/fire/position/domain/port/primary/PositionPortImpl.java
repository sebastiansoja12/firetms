package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.port.secondary.PositionServicePort;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionServicePort positionServicePort;

    private final PositionRepository positionRepository;

    @Override
    public List<Position> determineVehiclePosition(Truck truck) {
        final Position position = positionServicePort.determineVehiclePositions(truck);
        return positionRepository.save(position);
    }

    @Override
    public void determineVehiclePositionWithReport(Truck truck) {
        final List<Position> positions = positionRepository.findPositionsByPlate(truck.getPlate());
        positionServicePort.determineVehiclePosition(truck, positions);
    }

    @Override
    public void insertPosition(Position position) {
        position.setTimestamp(Instant.now().toString());
        positionRepository.savePosition(position);
    }
}
