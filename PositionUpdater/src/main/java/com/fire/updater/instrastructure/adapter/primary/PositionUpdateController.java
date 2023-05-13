package com.fire.updater.instrastructure.adapter.primary;

import com.fire.updater.domain.port.primary.PositionUpdatePort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
@AllArgsConstructor
public class PositionUpdateController {

    private final PositionUpdatePort positionUpdatePort;

    @GetMapping
    @Scheduled(fixedDelay = 300000)
    public void runManually() {
        positionUpdatePort.positionUpdateTransferList();
        new ResponseEntity<>(HttpStatus.CREATED);
    }
}
