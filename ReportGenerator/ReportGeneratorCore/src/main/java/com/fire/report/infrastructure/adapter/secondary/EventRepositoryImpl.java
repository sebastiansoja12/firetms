package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.*;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.EventModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final EventReadRepository eventReadRepository;

    private final EventModelMapper eventModelMapper;

    @Override
    public void save(Event event) {
        final EventEntity eventEntity = eventModelMapper.map(event);
        eventReadRepository.saveAndFlush(eventEntity);
    }

    @Override
    public void save(List<Event> events) {
        final List<EventEntity> eventEntities = eventModelMapper.map(events);
        eventReadRepository.saveAll(eventEntities);
    }

    @Override
    public ReportResponse findByVehicleReg(String vehicleReg, Pageable pageable) {

        final Page<EventEntity> eventEntities = eventReadRepository
                .findDistinctEventTimestampByVehicleReg(vehicleReg, pageable);

        final List<EventResponse> events = eventEntities.getContent().stream()
                .map(eventModelMapper::mapToReportEvent)
                .collect(Collectors.toList());

        final BorderCrossing borderCrossing = new BorderCrossing(vehicleReg, events);

        return new ReportResponse(new Report(borderCrossing));
    }

}
