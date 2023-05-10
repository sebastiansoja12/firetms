package com.fire.geocoding.infrastructure.adapter.primary;

import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.model.CountryResponse;
import com.fire.geocoding.domain.port.primary.GeocodePort;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingRequestMapper;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingResponseMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeocodingServiceAdapter implements GeocodingService {

    private final GeocodingRequestMapper requestMapper;

    private final GeocodingResponseMapper responseMapper;

    private final GeocodePort geocodePort;

    @Override
    public CountryResponseDto determineCountry(CoordinatesDto coordinates) {
        final Coordinate coordinate = requestMapper.map(coordinates);
        final CountryResponse countryResponse = geocodePort.determineCountry(coordinate);
        return responseMapper.map(countryResponse);
    }
}
