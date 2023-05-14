package com.fire.geocoding;

import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.model.CountryResponse;
import com.fire.geocoding.domain.port.primary.GeocodePort;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.geocoding.infrastructure.adapter.primary.GeocodingServiceAdapter;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingRequestMapper;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingRequestMapperImpl;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingResponseMapper;
import com.fire.geocoding.infrastructure.adapter.primary.mapper.GeocodingResponseMapperImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GeocodingServiceAdapterTest {

    private GeocodingServiceAdapter geocodingServiceAdapter;

    private final GeocodingRequestMapper requestMapper = new GeocodingRequestMapperImpl();

    private final GeocodingResponseMapper responseMapper = new GeocodingResponseMapperImpl();

    @Mock
    private GeocodePort geocodePort;

    @BeforeEach
    void setup() {
        geocodingServiceAdapter = new GeocodingServiceAdapter(requestMapper, responseMapper, geocodePort);
    }

    @Test
    void shouldDetermineCountry() {
        // given
        final CountryResponse countryResponse = new CountryResponse("POL");
        final CoordinatesDto coordinates = new CoordinatesDto(50.0, 20.0);
        final Coordinate coordinate = new Coordinate(50.0, 20.0);

        doReturn(countryResponse)
                .when(geocodePort)
                        .determineCountry(coordinate);

        // when
        final CountryResponseDto actualCountryResponse = geocodingServiceAdapter.determineCountry(coordinates);
        // then
        Assertions.assertThat(actualCountryResponse.getCountry()).isEqualTo(countryResponse.getCountry());

        // verify
        verify(geocodePort).determineCountry(coordinate);
    }
}
