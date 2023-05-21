package com.fire.position.configuration;

import com.fire.position.PositionService;
import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.domain.port.primary.PositionPortImpl;
import com.fire.position.domain.port.secondary.*;
import com.fire.position.domain.service.PositionDetectService;
import com.fire.position.domain.service.PositionDetectServiceImpl;
import com.fire.position.infrastructure.adapter.primary.PositionServiceAdapter;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import com.fire.position.infrastructure.adapter.secondary.PositionAdapter;
import com.fire.position.infrastructure.adapter.secondary.PositionReadRepository;
import com.fire.position.infrastructure.adapter.secondary.PositionRepositoryImpl;
import com.fire.position.infrastructure.adapter.secondary.mapper.PositionModelMapper;
import com.fire.report.LogEventPublisher;
import com.fire.truck.domain.port.primary.TruckPort;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PositionConfiguration {

    @Bean
    public PositionService positionService(PositionPort positionPort) {
        final PositionRequestMapper positionRequestMapper = Mappers.getMapper(PositionRequestMapper.class);
        return new PositionServiceAdapter(positionRequestMapper, positionPort);
    }

    @Bean
    public PositionPort positionPort(PositionRepository positionRepository,
                                     PositionDetectService positionDetectService) {
        final CalculateDistanceService calculateDistanceService = new CalculateDistanceServiceImpl();
        final InterpolationService interpolationService = new InterpolationServiceImpl();
        return new PositionPortImpl(positionDetectService, positionRepository,
                calculateDistanceService, interpolationService);
    }

    @Bean
    public PositionDetectService positionDetectService(PositionServicePort positionServicePort) {
        return new PositionDetectServiceImpl(positionServicePort);
    }

    @Bean
    public PositionServicePort positionServicePort(LogEventPublisher logEventPublisher, TruckPort truckPort) {
        return new PositionAdapter(logEventPublisher, truckPort);
    }

    @Bean
    public PositionRepository positionRepository(PositionReadRepository positionReadRepository) {
        final PositionModelMapper positionModelMapper = Mappers.getMapper(PositionModelMapper.class);
        return new PositionRepositoryImpl(positionReadRepository, positionModelMapper);
    }
}
