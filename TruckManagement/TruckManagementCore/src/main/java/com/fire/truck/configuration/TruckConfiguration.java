package com.fire.truck.configuration;

import com.fire.position.PositionService;
import com.fire.truck.domain.port.primary.TruckPort;
import com.fire.truck.domain.port.primary.TruckPortImpl;
import com.fire.truck.domain.port.secondary.TruckPositionServicePort;
import com.fire.truck.domain.port.secondary.TruckRepository;
import com.fire.truck.domain.service.TruckService;
import com.fire.truck.domain.service.TruckServiceImpl;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckRequestMapper;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckRequestMapperImpl;
import com.fire.truck.infrastructure.adapter.secondary.TruckPositionAdapter;
import com.fire.truck.infrastructure.adapter.secondary.TruckReadRepository;
import com.fire.truck.infrastructure.adapter.secondary.TruckRepositoryImpl;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckModelMapper;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionRequestMapper;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionResponseMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TruckConfiguration {


    @Bean
    public TruckPort truckPort(TruckService truckService) {
        return new TruckPortImpl(truckService);
    }

    @Bean
    public TruckService truckService(TruckRepository truckRepository,
        TruckPositionServicePort truckPositionServicePort) {
        return new TruckServiceImpl(truckRepository, truckPositionServicePort);
    }

    @Bean
    public TruckPositionServicePort truckPositionServicePort(PositionService positionService) {
        final TruckPositionRequestMapper requestMapper = Mappers.getMapper(TruckPositionRequestMapper.class);
        final TruckPositionResponseMapper responseMapper = Mappers.getMapper(TruckPositionResponseMapper.class);
        return new TruckPositionAdapter(positionService, requestMapper, responseMapper);
    }

    @Bean
    public TruckRepository truckRepository(TruckReadRepository truckReadRepository) {
        final TruckModelMapper truckModelMapper = Mappers.getMapper(TruckModelMapper.class);
        return new TruckRepositoryImpl(truckReadRepository, truckModelMapper);
    }

    @Bean
    public TruckRequestMapper truckRequestMapper() {
        return new TruckRequestMapperImpl();
    }
}
