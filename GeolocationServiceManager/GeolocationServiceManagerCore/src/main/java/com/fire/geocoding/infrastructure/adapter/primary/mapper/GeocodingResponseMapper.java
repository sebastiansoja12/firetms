package com.fire.geocoding.infrastructure.adapter.primary.mapper;

import com.fire.geocoding.domain.model.CountryResponse;
import com.fire.geocoding.dto.CountryResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface GeocodingResponseMapper {
    CountryResponseDto map(CountryResponse countryResponse);
}
