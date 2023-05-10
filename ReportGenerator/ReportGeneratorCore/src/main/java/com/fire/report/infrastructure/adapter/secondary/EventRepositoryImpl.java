package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.EventModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EventReadRepository eventReadRepository;

    private final EventModelMapper eventModelMapper;

    @Override
    public void save(Event event) {
        final EventEntity eventEntity = eventModelMapper.map(event);
        eventReadRepository.saveAndFlush(eventEntity);
    }
}
