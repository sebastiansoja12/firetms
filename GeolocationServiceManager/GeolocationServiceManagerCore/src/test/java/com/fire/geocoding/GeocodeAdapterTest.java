package com.fire.geocoding;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fire.geocoding.configuration.GeocodeServiceConfiguration;
import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.service.UrlJsonReaderService;
import com.fire.geocoding.infrastructure.adapter.secondary.GeocodeAdapter;
import com.fire.positionstack.PositionStackProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Disabled
public class GeocodeAdapterTest {

    @Mock
    private UrlJsonReaderService jsonReaderService;

    @Mock
    private PositionStackProperties positionStackProperties;

    private GeocodeAdapter geocodeAdapter;

    @Mock
    private GeocodeServiceConfiguration geocodeServiceConfiguration;

    @BeforeEach
    void setup() {
        geocodeAdapter = new GeocodeAdapter(jsonReaderService, positionStackProperties);
    }

    @Test
    public void shouldDetermineCountry() throws Exception {
        // given
        final Coordinate coordinate = new Coordinate(123.456, 789.012);
        final String countryCode = "US";
        final JsonNode jsonNode = JsonNodeFactory.instance.objectNode().put("country", countryCode);

        final URL url = new URL("http://example.com/");

        final String stage = "test";

        when(geocodeServiceConfiguration.getUrl()).thenReturn(url.getPath());
        when(geocodeServiceConfiguration.getStage()).thenReturn(stage);
        when(jsonReaderService.get(any(URL.class))).thenReturn(jsonNode);

        doReturn(jsonNode)
                .when(jsonReaderService)
                .get(any());

        // when
        String actualCountry = geocodeAdapter.determineCountry(coordinate);

        // then
        assertEquals(countryCode, actualCountry);
    }
}
