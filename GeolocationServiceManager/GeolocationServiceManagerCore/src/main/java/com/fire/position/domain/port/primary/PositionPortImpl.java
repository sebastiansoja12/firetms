package com.fire.position.domain.port.primary;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.PositionResponse;
import com.fire.position.domain.model.TruckPosition;
import com.fire.position.domain.port.secondary.CalculateDistanceService;
import com.fire.position.domain.port.secondary.InterpolationService;
import com.fire.position.domain.port.secondary.PositionRepository;
import com.fire.position.domain.service.PositionDetectService;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.collect.MoreCollectors.onlyElement;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionDetectService positionService;

    private final PositionRepository positionRepository;

    private final CalculateDistanceService calculateDistanceService;

    private final InterpolationService interpolationService;

    public static final Long MAX_DISTANCE_THRESHOLD = 10000L;


    @Override
    public Position insertPosition(Position position) {
        return positionRepository.savePosition(position);
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

    @Override
    public void determineNewestPosition(List<Position> positions) {
        positions.parallelStream()
                .forEach(
                    position -> {

                        final Position previousPosition = positionRepository
                                .findPositionOnPlate(position.getVehicleReg())
                                .map(this::map)
                                .orElse(position);

                        if (!isPositionValid(previousPosition, position)) {
                            position = interpolationService.interpolatePosition(position, previousPosition);
                        }

                        positionService.detectBorderCrossing(previousPosition, position);

                        positionRepository.savePosition(position);
                    }
        );
    }

    private boolean isPositionValid(Position position, Position newPosition) {
        final double distance =
                calculateDistanceService.calculateDistance(position.getCoordinate(), newPosition.getCoordinate());
        return distance <= MAX_DISTANCE_THRESHOLD;
    }

    private boolean isCountryDetermined(Position position) {
        return !position.getCountry().equals("NOT_DETERMINED");
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
}
