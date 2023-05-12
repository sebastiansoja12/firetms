package com.fire.updater.configuration;

import com.fire.geocoding.GeocodingService;
import com.fire.position.PositionService;
import com.fire.updater.domain.port.primary.PositionUpdatePort;
import com.fire.updater.domain.port.primary.PositionUpdatePortImpl;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import com.fire.updater.instrastructure.adapter.secondary.PositionUpdateAdapter;
import com.fire.updater.instrastructure.adapter.secondary.mapper.PositionMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PositionUpdaterConfiguration {

    @Bean
    public PositionUpdatePort positionUpdatePort(PositionUpdateServicePort positionUpdateServicePort) {
        return new PositionUpdatePortImpl(positionUpdateServicePort);
    }

    @Bean
    public PositionUpdateServicePort positionUpdateServicePort(PositionService positionService,
        GeocodingService geocodingService) {
        return new PositionUpdateAdapter(positionService, Mappers.getMapper(PositionMapper.class), geocodingService);
    }
}
