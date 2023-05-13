package com.fire.truck.infrastructure.adapter.secondary.mapper;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.infrastructure.adapter.secondary.entity.TruckEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TruckModelMapper {

    Truck map(TruckEntity truck);

    @Mapping(source = "vehicleReg", target = "plate")
    TruckEntity map(TruckRequest truck);
}
