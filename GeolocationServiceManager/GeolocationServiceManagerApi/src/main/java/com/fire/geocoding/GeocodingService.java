package com.fire.geocoding;

import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;

public interface GeocodingService {
    CountryResponseDto determineCountry(CoordinatesDto coordinates);
}
