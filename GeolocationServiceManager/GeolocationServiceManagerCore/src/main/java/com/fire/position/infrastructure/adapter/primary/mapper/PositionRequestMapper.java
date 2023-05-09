package com.fire.position.infrastructure.adapter.primary.mapper;

import com.fire.position.domain.model.Truck;
import com.fire.position.dto.TruckDto;
import org.mapstruct.Mapper;

@Mapper
public interface PositionRequestMapper {

    Truck map(TruckDto truck);
}
