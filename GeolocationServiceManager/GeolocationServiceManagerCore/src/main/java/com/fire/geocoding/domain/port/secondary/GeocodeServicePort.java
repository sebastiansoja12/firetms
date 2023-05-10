package com.fire.geocoding.domain.port.secondary;

import com.fire.geocoding.domain.model.Coordinate;

public interface GeocodeServicePort {
    String determineCountry(Coordinate coordinate);
}
