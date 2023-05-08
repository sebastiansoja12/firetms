package com.fire.truck.domain.service;

import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.model.Truck;

public interface TruckService {
    void addTruck(TruckRequest truck);

    Truck getTruckByPlate(String plate);
}
