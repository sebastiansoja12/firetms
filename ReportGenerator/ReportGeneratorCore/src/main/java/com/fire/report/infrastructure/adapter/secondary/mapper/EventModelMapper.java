package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface EventModelMapper {

    EventEntity map(Event event);
    List<EventEntity> map(List<Event> event);

    ReportResponse mapToReportResponse(Page<EventEntity> eventEntityPage);

    EventResponse mapToReportEvent(EventEntity eventEntity);
}
