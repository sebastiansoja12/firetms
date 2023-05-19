package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.Event;
import com.fire.report.domain.model.EventResponse;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.EventModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EventReadRepository eventReadRepository;

    private final EventModelMapper eventModelMapper;

    @Override
    public void save(Event event) {
        final EventEntity eventEntity = eventModelMapper.map(event);
        eventReadRepository.save(eventEntity);
    }

    @Override
    public void save(List<Event> events) {
        final List<EventEntity> eventEntities = eventModelMapper.map(events);
        eventReadRepository.saveAll(eventEntities);
    }

    @Override
    public List<EventResponse> findByVehicleReg(String vehicleReg, Pageable pageable) {

        final Page<EventEntity> eventEntities = eventReadRepository
                .findDistinctEventTimestampByVehicleReg(vehicleReg, pageable);

        return eventEntities.getContent().stream()
                .map(eventModelMapper::mapToReportEvent)
                .toList();
    }

    @Override
    public List<EventResponse> findByVehicleRegAndDates(String vehicleReg, Instant dateFrom, Instant dateTo) {
        final List<EventEntity> eventEntities = eventReadRepository
                .findEventsByVehicleRegAndDates(vehicleReg, dateFrom, dateTo);

        return eventEntities.stream()
                .map(eventModelMapper::mapToReportEvent)
                .toList();
    }

}
