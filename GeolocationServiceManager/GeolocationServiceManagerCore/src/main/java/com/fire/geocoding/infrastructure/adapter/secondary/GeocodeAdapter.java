package com.fire.geocoding.infrastructure.adapter.secondary;

import com.fasterxml.jackson.databind.JsonNode;
import com.fire.geocoding.domain.model.Coordinate;
import com.fire.geocoding.domain.port.secondary.GeocodeServicePort;
import com.fire.geocoding.domain.service.UrlJsonReaderService;
import com.fire.positionstack.TokenStageProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor
@Slf4j
public class GeocodeAdapter implements GeocodeServicePort {

    private final TokenStageProperties tokenStageProperties;

    private final UrlJsonReaderService jsonReaderService;

    private final String DATA = "data";

    private final String COUNTRY_CODE = "country_code";


    @Override
    public String determineCountry(Coordinate coordinate) {
        final String url = createRequest(coordinate.getLongitude() + "," + coordinate.getLatitude());
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

    private String createRequest(String coordinates) {
        return tokenStageProperties.createRequestLink(coordinates);
    }


    private URL urlConverter(String url) throws MalformedURLException {
        return new URL(url);
    }
}
