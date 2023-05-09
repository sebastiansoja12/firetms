package com.fire.report;

public interface LogEventPublisher {
    void send(LogEvent event);
}
