package com.fire.updater.configuration.mock;

import com.fire.position.PositionService;
import com.fire.position.dto.CoordinateDto;
import com.fire.position.dto.PositionDto;
import com.fire.updater.domain.model.PositionUpdateTransfer;
import com.fire.updater.domain.model.Vehicle;
import com.fire.updater.domain.port.secondary.PositionUpdateServicePort;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


public class TelemetryMockConfiguration {

    @AllArgsConstructor
    public static class PositionUpdateMockAdapter implements PositionUpdateServicePort {

        private final PositionService positionService;

        private final MockVehiclePositionUpdater mockVehiclePositionUpdater;

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
            final List<Vehicle> vehicles = mockVehiclePositionUpdater.getVehicles();
            return vehicles.stream()
                    .map(this::map)
                    .toList();
        }

        private PositionUpdateTransfer map(Vehicle vehicle) {
            final PositionUpdateTransfer positionUpdateTransfer = new PositionUpdateTransfer();
            positionUpdateTransfer.setLatitude(vehicle.getLatitude());
            positionUpdateTransfer.setLongitude(vehicle.getLongitude());
            positionUpdateTransfer.setTelemetryEnabled(vehicle.isTelemetryEnabled());
            positionUpdateTransfer.setVehicleReg(vehicle.getVehicleReg());
            positionUpdateTransfer.setTimeStamp(vehicle.getTimeStamp().toString());
            return positionUpdateTransfer;
        }
    }
}
