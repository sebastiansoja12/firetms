package com.fire.position.infrastructure.adapter.primary.mapper;

import com.fire.position.domain.model.Position;
import com.fire.position.dto.PositionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PositionResponseMapper {
    PositionDto map(Position position);

    List<PositionDto> map(List<Position> position);
}
