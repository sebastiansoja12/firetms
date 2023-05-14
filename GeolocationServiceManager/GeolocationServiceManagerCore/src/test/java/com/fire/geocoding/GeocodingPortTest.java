package com.fire.geocoding;

import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.model.CountryResponse;
import com.fire.geocoding.domain.port.primary.GeocodePort;
import com.fire.geocoding.domain.port.primary.GeocodePortImpl;
import com.fire.geocoding.domain.port.secondary.GeocodeServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GeocodingPortTest {


    @Mock
    private GeocodeServicePort geocodeServicePort;

    private GeocodePort geocodePort;

    @BeforeEach
    void setup() {
        geocodePort = new GeocodePortImpl(geocodeServicePort);
    }

    @Test
    void shouldDetermineCountry() {
        // given
        final Coordinate coordinate = new Coordinate(10.0, 10.0);
        final String expectedCountry = "POL";
        doReturn(expectedCountry)
                .when(geocodeServicePort)
                .determineCountry(coordinate);
        // when
        final CountryResponse countryResponse = geocodePort.determineCountry(coordinate);
        // then
        assertEquals(expectedCountry, countryResponse.getCountry());
    }


}
