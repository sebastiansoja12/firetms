package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Coordinate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalculateDistanceServiceImpl implements CalculateDistanceService {

    public static final int EARTH_RADIUS = 6371000;

    @Override
    public double calculateDistance(Coordinate c1, Coordinate c2) {
        final double lon1 = Math.toRadians(c1.getLongitude());
        final double lat1 = Math.toRadians(c1.getLatitude());
        final double lon2 = Math.toRadians(c2.getLongitude());
        final double lat2 = Math.toRadians(c2.getLatitude());

        final double dlon = lon2 - lon1;
        final double dlat = lat2 - lat1;

        final double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));


        return (double) EARTH_RADIUS * c;
    }
}
