package com.fire.truck.infrastructure.adapter.primary.mapper;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.dto.TruckResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TruckResponseMapper {

    @Mapping(source = "plate", target = "vehicleReg")
    TruckResponseDto map(Truck truck);
}
