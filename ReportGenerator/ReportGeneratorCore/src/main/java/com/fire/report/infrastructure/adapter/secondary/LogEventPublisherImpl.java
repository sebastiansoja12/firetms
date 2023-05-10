package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.LogEvent;
import com.fire.report.LogEventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@AllArgsConstructor
@Component
@Slf4j
public class LogEventPublisherImpl implements LogEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void send(LogEvent event) {
        logEvent(event);
        eventPublisher.publishEvent(event);
    }


    private void logEvent(LogEvent event) {
        log.info("Publishing event " + event.getClass().getSimpleName());
    }
}