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

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class PositionPortImpl implements PositionPort {

    private final PositionDetectService positionService;

    private final PositionRepository positionRepository;

    private final CalculateDistanceService calculateDistanceService;

    private final InterpolationService interpolationService;

    public static final Long MAX_DISTANCE_THRESHOLD = 10000L;

    private static final Duration MAX_TIME_DIFFERENCE = Duration.ofHours(1);



    @Override
    public Position insertPosition(Position position) {
        positionService.checkIfVehicleExists(position.getVehicleReg());
        return positionRepository.savePosition(position);
    }

    @Override
    public TruckPosition getVehiclePosition(String vehicleReg, int pageNumber, int pageSize) {

        positionService.checkIfVehicleExists(vehicleReg);

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

                        if (!isPositionValid(previousPosition, position) && isCountryNotDetermined(position) &&
                            !reactivatedGPSTracker(previousPosition, position)) {
                            position = interpolationService.interpolatePosition(position, previousPosition);
                        }

                        positionService.detectBorderCrossing(previousPosition, position);

                        positionRepository.savePosition(position);
                    }
        );
    }

    /**
     * Extra method to check if difference between two timestamps is shorter
     * than one hour when gps tracker was disconnected and did not send new positions
     * to the system. It prevents from interpolation the new position when vehicle
     * is much further than it was 60 minutes before.
     * @param previousPosition
     * @param currentPosition
     * @return true/false
     */
    private boolean reactivatedGPSTracker(Position previousPosition, Position currentPosition) {
        final Instant instantPreviousPosition = Instant.parse(previousPosition.getTimestamp());
        final Instant instantCurrentPosition = Instant.parse(currentPosition.getTimestamp());

        final Duration timeDifference = Duration.between(instantPreviousPosition, instantCurrentPosition);

        return timeDifference.getSeconds() <= MAX_TIME_DIFFERENCE.getSeconds();
    }

    private boolean isPositionValid(Position position, Position newPosition) {
        final double distance =
                calculateDistanceService.calculateDistance(position.getCoordinate(), newPosition.getCoordinate());
        return distance <= MAX_DISTANCE_THRESHOLD;
    }

    private boolean isCountryNotDetermined(Position position) {
        return position.getCountry().equals("NOT_DETERMINED");
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
