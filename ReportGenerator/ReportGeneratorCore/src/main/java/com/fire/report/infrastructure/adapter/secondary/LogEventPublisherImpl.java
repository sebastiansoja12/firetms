package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.LogEvent;
import com.fire.report.LogEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@AllArgsConstructor
@Component
public class LogEventPublisherImpl implements LogEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    private final Logger logger;

    @Override
    public void send(LogEvent event) {
        logEvent(event);
        eventPublisher.publishEvent(event);
    }


    private void logEvent(LogEvent event) {
        logger.info("Publishing event " + event.getClass().getSimpleName());
    }
}