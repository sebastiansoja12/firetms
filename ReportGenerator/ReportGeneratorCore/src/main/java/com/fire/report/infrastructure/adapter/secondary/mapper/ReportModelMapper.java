package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.Report;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReportModelMapper {

    ReportEntity map(Report report);

    EventEntity map(Event event);

    List<EventEntity> mapToEventEntity(List<Event> events);

    BorderCrossEntity map(BorderCrossing borderCrossing);

    List<BorderCrossEntity> mapToBorderCrossEntity(List<BorderCrossing> borderCrossings);

}
