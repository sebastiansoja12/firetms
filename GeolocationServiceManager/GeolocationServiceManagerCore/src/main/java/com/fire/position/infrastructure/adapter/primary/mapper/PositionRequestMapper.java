package com.fire.position.infrastructure.adapter.primary.mapper;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PositionRequestMapper {

    Truck map(TruckDto truck);

    Position map(PositionDto position);

    List<Position> map(List<PositionDto> positions);
}
