package com.fire.updater.configuration;

import com.fire.position.PositionService;
import com.fire.position.dto.CoordinateDto;
import com.fire.position.dto.PositionDto;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class TelemetryMockConfiguration {

    @AllArgsConstructor
    static class PositionUpdateMockAdapter implements PositionUpdateServicePort {

        private final PositionService positionService;

        @Override
        public void positionUpdateTransferList() {
           final List<PositionUpdateTransfer> positionUpdateTransferList = preparePositionTransfers();
            final List<PositionDto> positions = positionUpdateTransferList.stream()
                    .map(this::map)
                    .collect(Collectors.toList());

            positions.forEach(
                    p -> {
                        p.setCountry("MOCK");
                    }
            );
            positionService.getNewestPosition(positions);
        }

        private PositionDto map(PositionUpdateTransfer positionUpdateTransfer) {
            final PositionDto position = new PositionDto();
            final CoordinateDto coordinates = new CoordinateDto();
            coordinates.setLongitude(positionUpdateTransfer.getLongitude());
            coordinates.setLatitude(positionUpdateTransfer.getLatitude());
            position.setCoordinate(coordinates);
            position.setVehicleReg(positionUpdateTransfer.getVehicleReg());
            position.setTimestamp(positionUpdateTransfer.getTimeStamp());
            return position;
        }

        private List<PositionUpdateTransfer> preparePositionTransfers() {
            final PositionUpdateTransfer positionUpdateTransfer = new PositionUpdateTransfer();
            positionUpdateTransfer.setLatitude(19.0);
            positionUpdateTransfer.setLongitude(50.0);
            positionUpdateTransfer.setTelemetryEnabled(true);
            positionUpdateTransfer.setVehicleReg("SR1234");
            positionUpdateTransfer.setTimeStamp(Instant.now().toString());
            return List.of(positionUpdateTransfer);
        }
    }
}
