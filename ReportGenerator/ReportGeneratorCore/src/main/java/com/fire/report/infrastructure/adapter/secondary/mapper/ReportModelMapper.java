package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.BorderCrossing;
import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.Report;
import com.fire.report.domain.model.ReportResponse;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface ReportModelMapper {

    @Mapping(target = "borderCross", source = "borderCrossingEvent")
    ReportEntity map(Report report);

    EventEntity map(Event event);

    List<EventEntity> mapToEventEntity(List<Event> events);

    BorderCrossEntity map(BorderCrossing borderCrossing);

    List<BorderCrossEntity> mapToBorderCrossEntity(List<BorderCrossing> borderCrossings);

    @Mapping(source = "borderCross", target = "borderCrossingEvent")
    Report map(ReportEntity report);

    ReportResponse map(Page<Report> reports);

}
