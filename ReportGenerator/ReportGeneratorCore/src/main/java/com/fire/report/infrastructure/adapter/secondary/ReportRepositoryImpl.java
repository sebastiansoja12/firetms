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

    // TODO albo mozna zrobic tak ze tylko bedzie ten EventEntity??? i on moze miec vehicleReg w sobie bo w sumie chyba musi miec
    // i wtedy bedzie wiadomo kiedy skad wyjechal, a w bazie w position tez bedzie ta wartoisc i mozna by przy generowaniu raportu
    // to jakos sklecic i zrobic obiekt ktory ma te borderCrossingEvent ale to nie bedzie encja tylko obiekt w ktory zapisze sie
    // lista eventow
    @Override
    public Report findByVehicleReg(String vehicleReg) {
        final Optional<ReportEntity> report = reportReadRepository
                .findByVehicleRegWithEvents(vehicleReg);

        return report.map(reportModelMapper::map).orElseThrow(
                () -> new ReportNotFoundException("Report for " + vehicleReg + " does not exist")
        );
    }
}
