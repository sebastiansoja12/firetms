package com.fire.report.event;

import com.fire.report.LogEvent;
import com.fire.report.dto.TruckPositionMessageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class BorderCrossingDeterminationEvent extends RouteLogBaseEvent implements LogEvent {

    private final TruckPositionMessageDto truckPositionMessage;

    @Builder
    BorderCrossingDeterminationEvent(@NonNull TruckPositionMessageDto truckPositionMessage) {
        super();
        this.truckPositionMessage = truckPositionMessage;
    }

}
