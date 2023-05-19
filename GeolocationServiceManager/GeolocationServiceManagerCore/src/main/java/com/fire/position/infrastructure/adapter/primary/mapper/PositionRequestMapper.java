package com.fire.position.infrastructure.adapter.primary.mapper;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.PositionRequest;
import com.fire.position.domain.model.Truck;
import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PositionRequestMapper {

    Truck map(TruckDto truck);

    Position map(PositionDto position);

    @Mapping(target = "timestamp", ignore = true)
    Position map(PositionRequest position);

    List<Position> map(List<PositionDto> positions);
}
