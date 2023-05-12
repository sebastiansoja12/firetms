package com.fire.updater.instrastructure.adapter.secondary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.geocoding.GeocodingService;
import com.fire.geocoding.dto.CoordinatesDto;
import com.fire.geocoding.dto.CountryResponseDto;
import com.fire.position.PositionService;
import com.fire.position.dto.PositionDto;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import com.fire.updater.instrastructure.adapter.secondary.mapper.PositionMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PositionUpdateAdapter implements PositionUpdateServicePort {

    private final static String POSITION_URL = "http://localhost:8081/vehicles/coordinates";

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

    private static List<PositionUpdateTransfer> getPositionUpdateTransfers() {
        final RestTemplate restTemplate = new RestTemplate();
        final String json = restTemplate.getForObject(POSITION_URL, String.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        List<PositionUpdateTransfer> positionTransfer = new ArrayList<>();
        try {
            positionTransfer = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return positionTransfer;
    }
}
