package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.domain.model.Report;
import com.fire.report.domain.port.secondary.ReportRepository;
import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import com.fire.report.infrastructure.adapter.secondary.exception.ReportNotFoundException;
import com.fire.report.infrastructure.adapter.secondary.mapper.ReportModelMapper;
import lombok.AllArgsConstructor;

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
        reportReadRepository.save(reportEntity);
    }


    @Override
    public Report findByVehicleReg(String vehicleReg) {
        final Optional<ReportEntity> report = reportReadRepository
                .findByVehicleRegWithEvents(vehicleReg);

        return report.map(reportModelMapper::map).orElseThrow(
                () -> new ReportNotFoundException("Report for " + vehicleReg + " does not exist")
        );
    }
}
