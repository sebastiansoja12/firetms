package com.fire.report.configuration;

import com.fire.report.domain.port.primary.ReportControllerPort;
import com.fire.report.domain.port.primary.ReportControllerPortImpl;
import com.fire.report.domain.port.primary.ReportLogPort;
import com.fire.report.domain.port.primary.ReportLogPortImpl;
import com.fire.report.domain.port.secondary.BorderCrossRepository;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.domain.port.secondary.ReportRepository;
import com.fire.report.infrastructure.adapter.primary.mapper.EventMapper;
import com.fire.report.infrastructure.adapter.primary.mapper.EventMapperImpl;
import com.fire.report.infrastructure.adapter.secondary.*;
import com.fire.report.infrastructure.adapter.secondary.mapper.BorderModelMapper;
import com.fire.report.infrastructure.adapter.secondary.mapper.EventModelMapper;
import com.fire.report.infrastructure.adapter.secondary.mapper.ReportModelMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class ReportConfiguration {

    @Bean
    public ReportLogPort reportLogPort(ReportRepository reportRepository, BorderCrossRepository borderCrossRepository,
        EventRepository eventRepository) {
        return new ReportLogPortImpl(reportRepository, borderCrossRepository, eventRepository);
    }

    @Bean
    public ReportRepository reportRepository(ReportReadRepository reportReadRepository,
        BorderCrossReadRepository borderCrossReadRepository,
        EventReadRepository eventReadRepository) {
        final ReportModelMapper reportModelMapper = Mappers.getMapper(ReportModelMapper.class);
        return new ReportRepositoryImpl(reportReadRepository, reportModelMapper,
                borderCrossReadRepository, eventReadRepository);
    }

    @Bean
    public BorderCrossRepository borderCrossRepository(BorderCrossReadRepository borderCrossReadRepository) {
        final BorderModelMapper borderModelMapper = Mappers.getMapper(BorderModelMapper.class);
        return new BorderCrossRepositoryImpl(borderCrossReadRepository, borderModelMapper);
    }

    @Bean
    public EventRepository eventRepository(EventReadRepository eventReadRepository) {
        final EventModelMapper eventModelMapper = Mappers.getMapper(EventModelMapper.class);
        return new EventRepositoryImpl(eventReadRepository, eventModelMapper);
    }

    @Bean
    public EventMapper eventMapper() {
        return new EventMapperImpl();
    }


    @Bean
    public ReportControllerPort reportControllerPort(ReportRepository reportRepository,
                                                     EventRepository eventRepository) {
        return new ReportControllerPortImpl(reportRepository, eventRepository);
    }
}
