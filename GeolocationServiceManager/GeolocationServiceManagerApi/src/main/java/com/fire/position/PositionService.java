package com.fire.position;

import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;

import java.util.List;


public interface PositionService {
    List<PositionDto> determineVehiclesPosition(TruckDto truckDto);

    void determineVehiclesPositionWithReport(TruckDto truckDto);

    void getNewestPosition(List<PositionDto> position);
}
