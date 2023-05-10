package com.fire.report.infrastructure.adapter.primary.mapper;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.dto.BorderCrossingDto;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.ReportDto;
import com.fire.report.dto.TruckPositionMessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {

    TruckPositionMessage map(TruckPositionMessageDto truckPositionMessage);

    Report map(ReportDto report);

    BorderCrossing map(BorderCrossingDto borderCrossing);

    Event map(EventDto eventDto);

    List<Event> map(List<EventDto> events);

}
