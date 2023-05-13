package com.fire.updater.instrastructure.adapter.secondary.mapper;

import com.fire.position.dto.PositionDto;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PositionMapper {
    @Mapping(source = "longitude", target = "coordinate.longitude")
    @Mapping(source = "latitude", target = "coordinate.latitude")
    @Mapping(source = "timeStamp", target = "timestamp")
    @Mapping(target = "country", ignore = true)
    PositionDto map(PositionUpdateTransfer positionUpdateTransfer);

    List<PositionDto> map(List<PositionUpdateTransfer> positionUpdateTransfer);
}
