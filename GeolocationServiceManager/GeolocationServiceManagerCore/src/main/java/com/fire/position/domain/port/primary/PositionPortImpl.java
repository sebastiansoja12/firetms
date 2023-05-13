package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.*;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.service.PositionDetectService;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionDetectService positionService;

    private final PositionRepository positionRepository;


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
                        Position position = null;

                        final Optional<PositionEntity> positionEntity = positionRepository
                                .findPositionOnPlate(p.getVehicleReg());

                        if (positionEntity.isEmpty()) {
                            position = positionRepository.savePosition(p);
                        }


                        if (position == null) {
                            position = positionEntity.map(this::map).orElse(p);
                        }


                        final Position previousPosition = position;

                        final Position newPosition = positions.stream()
                                        .filter(this::isNotNull)
                                        .filter(e -> isVehicleRegEqual(previousPosition, e))
                                        .findFirst()
                                        .orElse(null);

                        positionService.detectBorderCrossing(previousPosition, newPosition);

                        if (positionEntity.isPresent()) {
                            positionRepository.savePosition(newPosition);
                        }

                    }
        );
    }

    @Override
    public TruckPosition getVehiclePosition(String vehicleReg, int pageNumber, int pageSize) {
        final List<PositionResponse> positions = positionRepository
                .findPositionsByPlate(vehicleReg, pageNumber, pageSize)
                .stream()
                .map(this::map)
                .toList();
        return new TruckPosition(vehicleReg, positions);
    }

    public boolean isVehicleRegEqual(Position position, Position newPosition) {
        return position.getVehicleReg().equals(newPosition.getVehicleReg());
    }

    private Position map(PositionEntity positionEntity) {
        return new Position(positionEntity.getVehicleReg(),
                new Coordinate(positionEntity.getLongitude(), positionEntity.getLatitude()),
                        positionEntity.getCountry(), positionEntity.getTimestamp().toString());
    }

    private PositionResponse map(Position position) {
        return new PositionResponse(position.getCoordinate(), position.getCountry(), position.getTimestamp());
    }

    private boolean isNotNull(Position position) {
        return Objects.nonNull(position);
    }
}
