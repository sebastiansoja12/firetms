package com.fire.position.infrastructure.adapter.secondary.mapper;

import com.fire.position.domain.model.Position;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PositionModelMapper {

    @Mapping(source = "longitude", target = "coordinate.longitude")
    @Mapping(source = "latitude", target = "coordinate.latitude")
    @Mapping(source = "timestamp", target = "timestamp")
    Position map(PositionEntity position);

    @Mapping(target = "longitude", source = "coordinate.longitude")
    @Mapping(target = "latitude", source = "coordinate.latitude")
    @Mapping(target = "timestamp", source = "timestamp")
    PositionEntity map(Position position);

    List<PositionEntity> mapToEntity(List<Position> positions);

    List<Position> map(List<PositionEntity> positionEntities);
}
