package com.fire.position;

import com.fire.position.dto.PositionDto;
import com.fire.position.dto.TruckDto;
import org.springframework.stereotype.Service;

import javax.swing.text.Position;
import java.util.List;

@Service
public interface PositionService {
    List<PositionDto> determineVehiclesPosition(TruckDto truckDto);

    void determineVehiclesPositionWithReport(TruckDto truckDto);

    void getNewestPosition(List<PositionDto> position);
}
