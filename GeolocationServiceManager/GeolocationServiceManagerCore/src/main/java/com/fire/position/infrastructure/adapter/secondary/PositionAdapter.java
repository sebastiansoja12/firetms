package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.report.LogEventPublisher;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.TruckPositionMessageDto;
import com.fire.report.event.TruckPositionDetermineEvent;
import com.fire.truck.domain.port.primary.TruckPort;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PositionAdapter implements PositionServicePort {

    private final LogEventPublisher logEventPublisher;

    private final TruckPort truckPort;

    @Override
    public void createBorderCrossingEvent(Position position, Position newPosition) {
        final EventDto event = EventDto.builder()
                .eventTimeStamp(Instant.now())
                .countryIn(newPosition.getCountry())
                .countryOut(position.getCountry())
                .vehicleReg(position.getVehicleReg())
                .build();

        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                .raportTimeStamp(Instant.now())
                .events(List.of(event))
                .build();

        sendEvent(buildEvent(message));
    }

    @Override
    public void checkIfVehicleExists(String vehicleReg) {
        truckPort.getTruckByPlate(vehicleReg);
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
