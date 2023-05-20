package com.fire.updater.instrastructure.adapter.secondary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.position.PositionService;
import com.fire.position.dto.CoordinateDto;
import com.fire.position.dto.PositionDto;
import com.fire.telemetry.TelemetryProperties;
import com.fire.updater.configuration.PositionServiceConfiguration;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import com.fire.updater.instrastructure.adapter.secondary.mapper.PositionMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionUpdateAdapter implements PositionUpdateServicePort {

    private final PositionService positionService;

    private final PositionMapper positionMapper;

    private final GeocodingService geocodingService;

    @NonNull
    private final TelemetryProperties telemetryProperties;

    @Override
    public void positionUpdateTransferList() {
        final PositionUpdaterAdapterConfiguration configuration = new PositionUpdaterAdapterConfiguration();
        final List<PositionUpdateTransfer> positionUpdateTransferList = getPositionUpdateTransfers(configuration)
                .stream()
                .filter(PositionUpdateTransfer::isTelemetryEnabled)
                .collect(Collectors.toList());

        final List<PositionDto> positions = positionMapper.map(positionUpdateTransferList);

        positions.forEach(p -> {
            final CountryResponseDto countryResponse = geocodingService.determineCountry(new CoordinatesDto(
                    p.getCoordinate().getLongitude(), p.getCoordinate().getLatitude()));

            if (isCountryValid(countryResponse)) {
                p.setCountry(countryResponse.getCountry());
            } else {
                setFallbackPositionAndCountry(p);
            }
        });
        positionService.getNewestPosition(positions);
    }

    private List<PositionUpdateTransfer> getPositionUpdateTransfers(
            PositionUpdaterAdapterConfiguration positionUpdaterAdapterConfiguration) {
        final RestTemplate restTemplate = new RestTemplate();
        final ObjectMapper objectMapper = new ObjectMapper();
        List<PositionUpdateTransfer> positionTransfer = new ArrayList<>();
        try {
            final String url = positionUpdaterAdapterConfiguration.getUrl();
            final String json = restTemplate.getForObject(url, String.class);
            positionTransfer = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return positionTransfer;
    }

    private boolean isCountryValid(CountryResponseDto countryResponse) {
        return countryResponse != null && !countryResponse.getCountry().equals("null");
    }

    private void setFallbackPositionAndCountry(PositionDto position) {
        position.setCoordinate(new CoordinateDto(0.0, 0.0));
        position.setCountry("NOT_DETERMINED");
    }

    private class PositionUpdaterAdapterConfiguration implements PositionServiceConfiguration {

        @Override
        public String getUrl() {
            return telemetryProperties.getUrl();
        }

        @Override
        public String getStage() {
            return telemetryProperties.getStage();
        }
    }
}
