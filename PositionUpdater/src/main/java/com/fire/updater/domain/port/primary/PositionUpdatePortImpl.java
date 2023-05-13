package com.fire.updater.domain.port.primary;

import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PositionUpdatePortImpl implements PositionUpdatePort {

    private final PositionUpdateServicePort positionUpdateServicePort;

    @Override
    public void positionUpdateTransferList() {
        log.info("Downloading positions of existing vehicles....");
        positionUpdateServicePort.positionUpdateTransferList();
    }
}
