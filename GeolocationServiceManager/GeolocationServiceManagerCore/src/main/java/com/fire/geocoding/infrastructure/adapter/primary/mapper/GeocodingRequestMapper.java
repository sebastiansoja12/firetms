package com.fire.geocoding.infrastructure.adapter.primary.mapper;

import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.dto.CoordinatesDto;
import org.mapstruct.Mapper;

@Mapper
public interface GeocodingRequestMapper {
    Coordinate map(CoordinatesDto coordinates);
}
