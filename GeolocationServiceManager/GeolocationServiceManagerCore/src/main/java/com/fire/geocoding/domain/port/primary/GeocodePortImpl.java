package com.fire.geocoding.domain.port.primary;

import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.model.CountryResponse;
import com.fire.geocoding.domain.port.secondary.GeocodeServicePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeocodePortImpl implements GeocodePort {

    private final GeocodeServicePort geocodeServicePort;

    @Override
    public CountryResponse determineCountry(Coordinate coordinates) {
        final String country = geocodeServicePort.determineCountry(coordinates);
        return new CountryResponse(country);
    }
}
