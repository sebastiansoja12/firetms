package com.fire.report.domain.port.secondary;

import com.fire.report.domain.model.Event;

public interface EventRepository {
    void save(Event event);
}
