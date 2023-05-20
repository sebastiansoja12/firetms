package com.fire.position.domain.port.secondary;

import com.fire.position.domain.model.Coordinate;

public interface CalculateDistanceService {

    double calculateDistance(Coordinate c1, Coordinate c2);
}
