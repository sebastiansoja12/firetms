package com.fire.report.configuration;

import com.fire.report.domain.port.primary.ReportControllerPort;
import com.fire.report.domain.port.primary.ReportControllerPortImpl;
import com.fire.report.domain.port.primary.ReportLogPort;
import com.fire.report.domain.port.primary.ReportLogPortImpl;
import com.fire.report.domain.port.secondary.EventRepository;
import com.fire.report.infrastructure.adapter.primary.mapper.EventMapper;
import com.fire.report.infrastructure.adapter.primary.mapper.EventMapperImpl;
import com.fire.report.infrastructure.adapter.secondary.*;
import com.fire.report.infrastructure.adapter.secondary.mapper.EventModelMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfiguration {

    @Bean
    public ReportLogPort reportLogPort(EventRepository eventRepository) {
        return new ReportLogPortImpl(eventRepository);
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
    public ReportControllerPort reportControllerPort(EventRepository eventRepository) {
        return new ReportControllerPortImpl(eventRepository);
    }
}
