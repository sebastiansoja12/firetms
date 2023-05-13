package com.fire.geocoding.configuration;

import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.domain.port.primary.GeocodePort;
import com.fire.geocoding.domain.port.primary.GeocodePortImpl;
import com.fire.geocoding.domain.port.secondary.GeocodeServicePort;
import com.fire.geocoding.domain.service.UrlJsonReaderService;
import com.fire.geocoding.domain.service.UrlReaderServiceImpl;
import com.fire.geocoding.infrastructure.adapter.primary.GeocodingServiceAdapter;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingRequestMapper;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingResponseMapper;
import com.fire.geocoding.infrastructure.adapter.secondary.GeocodeAdapter;
import com.fire.positionstack.PositionStackProperties;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeocodingConfiguration {

    @Bean
    public GeocodePort geocodePort(GeocodeServicePort geocodeServicePort) {
        return new GeocodePortImpl(geocodeServicePort);
    }

    @Bean
    public GeocodingService geocodingService(GeocodePort geocodePort) {
        final GeocodingRequestMapper requestMapper = Mappers.getMapper(GeocodingRequestMapper.class);
        final GeocodingResponseMapper responseMapper = Mappers.getMapper(GeocodingResponseMapper.class);
        return new GeocodingServiceAdapter(requestMapper, responseMapper, geocodePort);
    }

    @Bean
    public GeocodeServicePort geocodeServicePort(UrlJsonReaderService jsonReaderService,
                                                 PositionStackProperties positionStackProperties) {
        return new GeocodeAdapter(jsonReaderService, positionStackProperties);
    }

    @Bean
    public UrlJsonReaderService jsonReaderService() {
        return new UrlReaderServiceImpl();
    }
}
