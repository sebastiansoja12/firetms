package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.Report;
import com.fire.report.domain.port.secondary.ReportRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import com.fire.report.infrastructure.adapter.secondary.exception.ReportNotFoundException;
import com.fire.report.infrastructure.adapter.secondary.mapper.ReportModelMapper;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {

    private final ReportReadRepository reportReadRepository;

    private final ReportModelMapper reportModelMapper;

    private final BorderCrossReadRepository borderCrossReadRepository;

    private final EventReadRepository eventReadRepository;

    @Override
    public void saveReport(Report report) {
        final ReportEntity reportEntity = reportModelMapper.map(report);
        final List<EventEntity> eventEntities = reportModelMapper.mapToEventEntity(report.getBorderCrossingEvent().getEvents());
        final BorderCrossEntity borderCrossEntity = reportModelMapper.map(report.getBorderCrossingEvent());
        borderCrossReadRepository.save(borderCrossEntity);
        eventEntities.forEach(
                eventEntity -> {
                    eventEntity.setBorderCross(borderCrossEntity);
                }
        );
        eventReadRepository.saveAll(eventEntities);
        reportEntity.setBorderCross(borderCrossEntity);
        reportReadRepository.save(reportEntity);
    }

    @Override
    public Report findByVehicleReg(String vehicleReg) {
        final Optional<ReportEntity> report = reportReadRepository
                .findByVehicleRegWithEvents(vehicleReg)
                .stream()
                .findFirst();

        return report.map(reportModelMapper::map).orElseThrow(
                () -> new ReportNotFoundException("Report for " + vehicleReg + " does not exist")
        );
    }
}
