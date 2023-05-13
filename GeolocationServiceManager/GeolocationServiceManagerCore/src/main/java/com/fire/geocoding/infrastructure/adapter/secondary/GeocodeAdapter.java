package com.fire.geocoding.infrastructure.adapter.secondary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fire.geocoding.configuration.GeocodeServiceConfiguration;
import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.port.secondary.GeocodeServicePort;
import com.fire.geocoding.domain.service.UrlJsonReaderService;
import com.fire.positionstack.PositionStackProperties;
import com.fire.properties.GeoProperties;
import com.fire.telemetry.TelemetryProperties;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor
@Slf4j
public class GeocodeAdapter implements GeocodeServicePort {

    private final UrlJsonReaderService jsonReaderService;

    @NonNull
    private final PositionStackProperties positionStackProperties;

    private final String DATA = "data";

    private final String COUNTRY_CODE = "country_code";


    @Override
    public String determineCountry(Coordinate coordinate) {
        final GeocodeAdapterConfiguration configuration = new GeocodeAdapterConfiguration();
        return determineCountry(coordinate, configuration);
    }

    private String determineCountry(Coordinate coordinate, GeocodeAdapterConfiguration geocodeAdapterConfiguration) {
        final String url = geocodeAdapterConfiguration.getUrl() + coordinates(coordinate);
        JsonNode jsonNode = null;
        try {
            final URL requestUrl = urlConverter(url);
            jsonNode = result(requestUrl);
        } catch (MalformedURLException e) {
            log.error("Error registered: {0} ", e.getCause());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonNode.get(DATA).get(0).get(COUNTRY_CODE).asText();
    }

    private JsonNode result(URL url) throws IOException {
        return jsonReaderService.get(url);
    }

    public String coordinates(Coordinate coordinate) {
        return coordinate.getLongitude() + "," + coordinate.getLatitude();
    }


    private URL urlConverter(String url) throws MalformedURLException {
        return new URL(url);
    }

    private class GeocodeAdapterConfiguration implements GeocodeServiceConfiguration {

        @Override
        public String getUrl() {
            return positionStackProperties.getUrl();
        }

        @Override
        public String getStage() {
            return positionStackProperties.getStage();
        }
    }
}
