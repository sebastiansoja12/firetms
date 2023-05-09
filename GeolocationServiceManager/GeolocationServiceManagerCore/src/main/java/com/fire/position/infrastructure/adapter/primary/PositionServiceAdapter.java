package com.fire.position.infrastructure.adapter.primary;

import com.fire.position.PositionService;
import com.fire.position.domain.model.Truck;
import com.fire.position.dto.TruckDto;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionResponseMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionServiceAdapter implements PositionService {

    private final PositionRequestMapper positionRequestMapper;

    private final PositionResponseMapper positionResponseMapper;

    @Override
    public void determineVehiclesPosition(TruckDto truckDto) {
        final Truck truck = positionRequestMapper.map(truckDto);
    }
}
