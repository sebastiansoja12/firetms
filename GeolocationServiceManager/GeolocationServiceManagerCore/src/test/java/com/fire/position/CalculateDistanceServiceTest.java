package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.port.secondary.CalculateDistanceService;
import com.fire.position.domain.port.secondary.CalculateDistanceServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateDistanceServiceTest {

    private final CalculateDistanceService calculateDistanceService = new CalculateDistanceServiceImpl();

    @Test
    void shouldCalculateDistance() {
        // given
        final Coordinate c1 = new Coordinate();
        c1.setLatitude(50.0);
        c1.setLongitude(10.0);

        final Coordinate c2 = new Coordinate();
        c2.setLatitude(51.0);
        c2.setLongitude(11.0);

        // when
        final double result = calculateDistanceService.calculateDistance(c1, c2);
        // then nearest depot is KT1
        assertThat(result).isGreaterThan(0);
    }

}
