package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.secondary.ReportRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import com.fire.report.infrastructure.adapter.secondary.mapper.ReportModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {

    private final ReportReadRepository reportReadRepository;

    private final ReportModelMapper reportModelMapper;

    @Override
    public void saveReport(TruckPositionMessage truckPositionMessage) {
        final ReportEntity report = reportModelMapper.map(truckPositionMessage);
        reportReadRepository.save(report);
    }
}
