package com.fire.truck.infrastructure.adapter.secondary.mapper;

import com.fire.position.dto.TruckDto;
import com.fire.truck.domain.model.Truck;
import org.mapstruct.Mapper;

@Mapper
public interface TruckPositionRequestMapper {
    TruckDto map(Truck truck);
}
