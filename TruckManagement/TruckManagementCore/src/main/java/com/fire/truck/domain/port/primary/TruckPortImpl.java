package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.service.TruckService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TruckPortImpl implements TruckPort {

    private final TruckService truckService;


    @Override
    public void addTruck(List<TruckRequest> truckRequests) {
        truckRequests
                .forEach(truckService::addTruck);
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return truckService.getTruck(plate);
    }
}
