package com.fire.position.infrastructure.adapter.primary;

import com.fire.position.PositionService;
import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.Truck;
import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionResponseMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PositionServiceAdapter implements PositionService {

    private final PositionRequestMapper positionRequestMapper;

    private final PositionResponseMapper positionResponseMapper;

    private final PositionPort positionPort;

    @Override
    public List<PositionDto> determineVehiclesPosition(TruckDto truckDto) {
        final Truck truck = positionRequestMapper.map(truckDto);
        final List<Position> position = positionPort.determineVehiclePosition(truck);
        return positionResponseMapper.map(position);
    }

    @Override
    public void determineVehiclesPositionWithReport(TruckDto truckDto) {
        final Truck truck = positionRequestMapper.map(truckDto);
        positionPort.determineVehiclePositionWithReport(truck);
    }
}
