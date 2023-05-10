package com.fire.position.infrastructure.adapter.secondary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fire.position.domain.model.Coordinate;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.position.domain.vo.PositionTransfer;
import com.fire.report.LogEventPublisher;
import com.fire.report.dto.BorderCrossingDto;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.ReportDto;
import com.fire.report.dto.TruckPositionMessageDto;
import com.fire.report.event.TruckPositionDetermineEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PositionAdapter implements PositionServicePort {

    private final LogEventPublisher logEventPublisher;

    private final static String POSITION_URL = "http://localhost:8081/vehicles/coordinates/";



    @Override
    public void determineVehiclePosition(Truck truck, Position position) {
        // event
        final BorderCrossingDto borderCrossing = BorderCrossingDto.builder()
                .vehicleReg(truck.getPlate())
                .events(EventDto.builder()
                        .eventTimeStamp(Instant.now())
                        .countryIn(position.getCountry())
                        .countryOut("Germany")
                        .build())
                .build();

        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                .raportTimeStamp(Instant.now())
                .report(ReportDto.builder()
                        .borderCrossingEvent(borderCrossing).build())
                .build();

        sendEvent(buildEvent(message));
    }

    @Override
    public Position determineVehiclePositions(Truck truck) {
        final RestTemplate restTemplate = new RestTemplate();
        final String json = restTemplate.getForObject(POSITION_URL + truck.getPlate(), String.class);
        final ObjectMapper objectMapper = new ObjectMapper();
        PositionTransfer positionTransfer = null;
        try {
            positionTransfer = objectMapper.readValue(json, PositionTransfer.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final Position position = new Position();
        position.setCoordinate(Coordinate.builder()
                .latitude(positionTransfer.getLatitude())
                .longitude(positionTransfer.getLongitude())
                .build());
        position.setVehicleReg(truck.getPlate());
        position.setCountry("Poland");
        position.setTimestamp(Instant.now().toString());
        return position;
    }

    private TruckPositionDetermineEvent buildEvent(TruckPositionMessageDto message) {
        return TruckPositionDetermineEvent.builder()
                .truckPositionMessage(message)
                .build();
    }

    public void sendEvent(TruckPositionDetermineEvent event) {
        logEventPublisher.send(event);
    }
}
