package com.fire.truck;

import com.fire.position.PositionService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = { "com.fire.truck"})
@EntityScan(basePackages = { "com.fire.truck"})
@EnableJpaRepositories(basePackages = { "com.fire.truck"})
public class TruckTestConfiguration {

    @MockBean
    public PositionService positionService;

}
