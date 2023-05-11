package com.fire.report.domain.model;

import com.fire.report.domain.exception.EventMissingException;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.List;

@Data
public class TruckPositionMessage {

    private Instant raportTimeStamp;

    private List<Event> events;

    public List<Event> getEvents() {
        if (CollectionUtils.isEmpty(this.events)) {
            throw new EventMissingException("There are no border crossing events detected for this vehicle");
        }
        return this.events;
    }
}
