package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.position.domain.service.PositionDetectService;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionDetectService positionService;

    private final PositionServicePort positionServicePort;

    private final PositionRepository positionRepository;

    // tutaj ta logika z tym eventem Event ktory zapisze informacje o przekroczeniu granicy,
    // jezeli pierwszy wyciagniety rekord z bazy ma ten sam kod kraju to nie wysylamy tego eventu, a jak inny to wtedy tak
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

    @Override
    public void determineNewestPosition(List<Position> positions) {

        positions
                .forEach(
                    p -> {
                        if (!positionRepository.checkIfExistsAlreadyPreviousPosition(p)) {
                            positionRepository.savePosition(p);
                        }
                        final Position position = positionRepository.findPositionByPlate(p.getVehicleReg());

                        final Position newPosition = positions.stream()
                                        .filter(e -> e.getVehicleReg().equals(p.getVehicleReg()))
                                        .findFirst()
                                        .orElse(null);

                        positionService.detectBorderCrossing(position, newPosition);

                        positionRepository.savePosition(newPosition);
                    }
        );
    }
}
