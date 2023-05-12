package com.fire.truck.domain.service;

import com.fire.truck.domain.model.Position;
import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.secondary.TruckPositionServicePort;
import com.fire.truck.domain.port.secondary.TruckRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    private final TruckPositionServicePort truckPositionServicePort;

    @Override
    public void addTruck(TruckRequest truck) {
        truckRepository.saveTruck(truck);
    }

    @Override
    public TruckPositionResponse getTruckWithPosition(String plate) {
        final Truck truck = truckRepository.findByPlate(plate);
        final List<Position> position = truckPositionServicePort.determinePosition(truck);
        return new TruckPositionResponse(plate, position);
    }

    @Override
    public void getTruckPositionWithReport(String plate) {
        final Truck truck = truckRepository.findByPlate(plate);
        truckPositionServicePort.determinePositionWithReport(truck);
    }


    @Override
    public Truck getTruck(String plate) {
        return truckRepository.findByPlate(plate);
    }
}
