package com.fire.report.infrastructure.adapter.secondary.mapper;

import com.fire.report.domain.model.Event;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EventModelMapper {

    EventEntity map(Event event);
}
