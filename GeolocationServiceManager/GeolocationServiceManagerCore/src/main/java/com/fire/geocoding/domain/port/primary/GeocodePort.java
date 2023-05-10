package com.fire.geocoding.domain.port.primary;

import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.model.CountryResponse;

public interface GeocodePort {
    CountryResponse determineCountry(Coordinate coordinates);

}
