package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.Report;
import com.fire.report.domain.port.secondary.ReportRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.BorderCrossEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.ReportModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {

    private final ReportReadRepository reportReadRepository;

    private final ReportModelMapper reportModelMapper;

    private final BorderCrossReadRepository borderCrossReadRepository;

    private final EventReadRepository eventReadRepository;

    @Override
    public void saveReport(Report report) {
        final ReportEntity reportEntity = reportModelMapper.map(report);
        final EventEntity eventEntities = reportModelMapper.map(report.getBorderCrossingEvent().getEvents());
        final BorderCrossEntity borderCrossEntities = reportModelMapper.map(report.getBorderCrossingEvent());
        eventReadRepository.save(eventEntities);
        borderCrossEntities.setEvent(eventEntities);
        borderCrossReadRepository.save(borderCrossEntities);
        reportEntity.setBorderCross(borderCrossEntities);
        reportReadRepository.save(reportEntity);
    }
}
