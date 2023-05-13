package com.fire.truck.infrastructure.adapter.primary.mapper;

import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.dto.TruckRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TruckRequestMapper {

    List<TruckRequest> map(List<TruckRequestDto> truckRequest);
    @Mapping(source = "telemetryEnabled", target = "telematicsEnabled")
    TruckRequest map(TruckRequestDto truckRequest);
}
