package com.fire.report.infrastructure.adapter.primary;

import com.fire.report.domain.model.TruckPositionMessage;
import com.fire.report.domain.port.primary.ReportLogPort;
import com.fire.report.event.RouteLogBaseEvent;
import com.fire.report.event.TruckPositionDetermineEvent;
import com.fire.report.infrastructure.adapter.primary.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
@Component
public class LogEventListener {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSS");

    private final EventMapper eventMapper;

    private final ReportLogPort reportLogPort;


    @EventListener
    public void saveTruckPositionMessage(TruckPositionDetermineEvent event) {
        logEvent(event);
        final TruckPositionMessage message = eventMapper.map(event.getTruckPositionMessage());
        reportLogPort.saveTruckPositionMessage(message);
    }

    private void logEvent(RouteLogBaseEvent event) {
        log.info("Detected event " + event.getClass().getSimpleName() + " at " +
                event.getLocalDateTime().format(FORMATTER));
    }
}
