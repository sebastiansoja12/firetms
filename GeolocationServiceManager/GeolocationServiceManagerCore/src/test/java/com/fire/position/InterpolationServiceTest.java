package com.fire.position;

import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.InterpolationService;
import com.fire.position.domain.port.secondary.InterpolationServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpolationServiceTest {
    private final InterpolationService interpolationService = new InterpolationServiceImpl();


    @Test
    void shouldInterpolatePosition() {
        // given
        final Position position = Position.builder()
                .country("POL")
                .vehicleReg("SR1234")
                .timestamp(Instant.now().toString())
                .coordinate(new Coordinate(50.0, 10.0))
                .build();

        final Position newPosition = Position.builder()
                .country("POL")
                .vehicleReg("SR1234")
                .timestamp(Instant.now().toString())
                .coordinate(new Coordinate(60.0, 15.0))
                .build();
        // when
        final Position interpolation = interpolationService.interpolatePosition(position, newPosition);
        // then
        assertThat(interpolation).isNotNull();

        // and
        assertThat(interpolation.getCoordinate().getLongitude())
                .isNotEqualTo(position.getCoordinate().getLongitude());
    }
}
