package com.fire.truck.domain.service;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.secondary.TruckRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    @Override
    public void addTruck(TruckRequest truck) {
        truckRepository.saveTruck(truck);
    }


    @Override
    public Truck getTruck(String plate) {
        return truckRepository.findByPlate(plate);
    }
}
