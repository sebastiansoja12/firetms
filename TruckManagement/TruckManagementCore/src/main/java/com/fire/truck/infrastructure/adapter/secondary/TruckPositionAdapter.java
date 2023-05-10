package com.fire.truck.infrastructure.adapter.secondary;

import com.fire.position.PositionService;
import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import com.fire.truck.domain.model.Position;
import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.port.secondary.TruckPositionServicePort;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionRequestMapper;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckPositionResponseMapper;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TruckPositionAdapter implements TruckPositionServicePort {

    private final PositionService positionService;

    private final TruckPositionRequestMapper truckPositionRequestMapper;

    private final TruckPositionResponseMapper truckPositionResponseMapper;

    @Override
    public List<Position> determinePosition(Truck truck) {
        final TruckDto truckRequest = truckPositionRequestMapper.map(truck);
        final List<PositionDto> position = positionService.determineVehiclesPosition(truckRequest);
        return truckPositionResponseMapper.map(position);
    }

    @Override
    public void determinePositionWithReport(Truck truck) {
        final TruckDto truckRequest = truckPositionRequestMapper.map(truck);
        positionService.determineVehiclesPositionWithReport(truckRequest);
    }
}
