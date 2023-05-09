package com.fire.report.infrastructure.adapter.primary.mapper;

import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.dto.TruckPositionMessageDto;
import org.mapstruct.Mapper;

@Mapper
public interface EventMapper {

    TruckPositionMessage map(TruckPositionMessageDto truckPositionMessage);

}
