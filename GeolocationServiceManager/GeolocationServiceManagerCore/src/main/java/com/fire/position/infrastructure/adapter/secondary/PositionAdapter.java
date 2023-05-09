package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.secondary.PositionServicePort;
import com.fire.report.LogEventPublisher;
import com.fire.report.dto.BorderCrossingDto;
import com.fire.report.dto.EventDto;
import com.fire.report.dto.ReportDto;
import com.fire.report.dto.TruckPositionMessageDto;
import com.fire.report.event.TruckPositionDetermineEvent;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class PositionAdapter implements PositionServicePort {

    private final LogEventPublisher logEventPublisher;


    @Override
    public void determineVehiclePosition(Truck truck, Position position) {
        // event
        final BorderCrossingDto borderCrossing = BorderCrossingDto.builder()
                .vehicleReg(truck.getPlate())
                .event(List.of(EventDto.builder()
                        .eventTimeStamp(Instant.now())
                        .countryIn("Poland")
                        .countryOut("Germany")
                        .build()))
                .build();

        final TruckPositionMessageDto message = TruckPositionMessageDto.builder()
                .raportTimeStamp(Instant.now())
                .report(ReportDto.builder()
                        .borderCrossingEvent(List.of(borderCrossing)).build())
                .build();

        sendEvent(buildEvent(message));
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
