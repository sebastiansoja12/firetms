package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;

/**
 * If the new position is futher than given constant value
 * then with interpolation service new position will get
 * coordinates and country of the previous position to
 * prevent situation when position without country code
 * or without coordinates will be saved in database
 */
public class InterpolationServiceImpl implements InterpolationService {
    @Override
    public Position interpolatePosition(Position currentPosition, Position previousPosition) {
        final String vehicleReg = currentPosition.getVehicleReg();
        final double interpolatedLongitude = previousPosition.getCoordinate().getLongitude();
        final double interpolatedLatitude = previousPosition.getCoordinate().getLatitude();


        return new Position(vehicleReg, new Coordinate(interpolatedLongitude, interpolatedLatitude),
                previousPosition.getCountry(), currentPosition.getTimestamp());
    }
}
