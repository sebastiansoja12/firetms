package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.EventResponse;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EventModelMapper {

    @Mapping(target = "id", ignore = true)
    EventEntity map(Event event);

    List<EventEntity> map(List<Event> event);

    EventResponse mapToReportEvent(EventEntity eventEntity);
}
