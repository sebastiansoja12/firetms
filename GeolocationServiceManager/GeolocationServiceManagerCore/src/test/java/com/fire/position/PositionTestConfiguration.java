package com.fire.position;

import com.fire.geocoding.GeocodingService;
import com.fire.report.LogEventPublisher;
import com.fire.truck.domain.port.primary.TruckPort;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.fire.position"})
@EntityScan(basePackages = { "com.fire.position"})
@EnableJpaRepositories(basePackages = { "com.fire.position"})
public class PositionTestConfiguration {

    @MockBean
    public LogEventPublisher logEventPublisher;

    @MockBean
    public GeocodingService geocodingService;

    @MockBean
    public TruckPort truckPort;
}
