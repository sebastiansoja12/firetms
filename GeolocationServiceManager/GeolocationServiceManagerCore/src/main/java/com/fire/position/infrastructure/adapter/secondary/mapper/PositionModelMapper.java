package com.fire.position.infrastructure.adapter.secondary.mapper;

import com.fire.position.domain.model.Position;
import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PositionModelMapper {

    Position map(PositionEntity position);
}
