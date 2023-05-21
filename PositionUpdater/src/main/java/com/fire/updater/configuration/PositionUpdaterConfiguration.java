package com.fire.updater.configuration;

import com.fire.geocoding.GeocodingService;
import com.fire.position.PositionService;
import com.fire.telemetry.TelemetryProperties;
import com.fire.updater.configuration.mock.GeocodeMockService;
import com.fire.updater.configuration.mock.MockVehiclePositionUpdater;
import com.fire.updater.configuration.mock.TelemetryMockConfiguration;
import com.fire.updater.domain.port.primary.PositionUpdatePort;
import com.fire.updater.domain.port.primary.PositionUpdatePortImpl;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import com.fire.updater.instrastructure.adapter.secondary.PositionUpdateAdapter;
import com.fire.updater.instrastructure.adapter.secondary.mapper.PositionMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PositionUpdaterConfiguration {

    @Bean
    public PositionUpdatePort positionUpdatePort(PositionUpdateServicePort positionUpdateServicePort) {
        return new PositionUpdatePortImpl(positionUpdateServicePort);
    }

    @Bean
    @ConditionalOnProperty(name="service.mock", havingValue="false")
    public PositionUpdateServicePort positionUpdateServicePort(PositionService positionService,
         GeocodingService geocodingService, TelemetryProperties telemetryProperties) {
        return new PositionUpdateAdapter(positionService,
                Mappers.getMapper(PositionMapper.class), geocodingService, telemetryProperties);
    }

    @Bean
    @ConditionalOnProperty(name="service.mock", havingValue="true")
    public PositionUpdateServicePort positionUpdateMockServicePort(PositionService positionService,
        MockVehiclePositionUpdater mockVehiclePositionUpdater, GeocodeMockService geocodeMockService) {
        return new TelemetryMockConfiguration.PositionUpdateMockAdapter(positionService, mockVehiclePositionUpdater,
                geocodeMockService);
    }
}
