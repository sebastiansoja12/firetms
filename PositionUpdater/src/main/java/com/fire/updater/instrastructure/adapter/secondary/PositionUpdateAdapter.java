package com.fire.updater.instrastructure.adapter.secondary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.position.PositionService;
import com.fire.position.dto.PositionDto;
import com.fire.telemetry.TelemetryProperties;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import com.fire.updater.instrastructure.adapter.secondary.mapper.PositionMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionUpdateAdapter extends TelemetryProperties implements PositionUpdateServicePort {

    private final PositionService positionService;

    private final PositionMapper positionMapper;

    private final GeocodingService geocodingService;

    @Override
    public void positionUpdateTransferList() {
        final List<PositionUpdateTransfer> positionUpdateTransferList = getPositionUpdateTransfers()
                .stream()
                .filter(PositionUpdateTransfer::isTelemetryEnabled)
                .collect(Collectors.toList());

        final List<PositionDto> positions = positionMapper.map(positionUpdateTransferList);

        positions.forEach(
                p -> {
                    final CountryResponseDto countryResponse = geocodingService.determineCountry(new CoordinatesDto(
                            p.getCoordinate().getLongitude(), p.getCoordinate().getLatitude()));
                    p.setCountry(countryResponse.getCountry());
                }
        );
        positionService.getNewestPosition(positions);
    }

    private List<PositionUpdateTransfer> getPositionUpdateTransfers() {
        final RestTemplate restTemplate = new RestTemplate();
        final ObjectMapper objectMapper = new ObjectMapper();
        List<PositionUpdateTransfer> positionTransfer = new ArrayList<>();
        try {
            final String json = restTemplate.getForObject(getUrl(), String.class);
            positionTransfer = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return positionTransfer;
    }
}
