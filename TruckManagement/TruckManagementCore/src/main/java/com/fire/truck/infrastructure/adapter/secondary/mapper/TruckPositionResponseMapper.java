package com.fire.truck.infrastructure.adapter.secondary.mapper;

import com.fire.position.dto.PositionDto;
import com.fire.truck.domain.model.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TruckPositionResponseMapper {

    Position map(PositionDto position);

    List<Position> map(List<PositionDto> positions);
}
