package com.fire.truck.infrastructure.adapter.primary.mapper;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.dto.TruckResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface TruckResponseMapper {

    TruckResponseDto map(Truck truck);
}
