package com.fire.report.event;

import com.fire.report.LogEvent;
import com.fire.report.dto.TruckPositionMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TruckPositionDetermineEvent extends RouteLogBaseEvent implements LogEvent {

    private final TruckPositionMessageDto truckPositionMessage;

    @Builder
    TruckPositionDetermineEvent(@NonNull TruckPositionMessageDto truckPositionMessage) {
        super();
        this.truckPositionMessage = truckPositionMessage;
    }

}
