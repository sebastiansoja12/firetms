package com.fire.truck.domain.service;

import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.model.Truck;

public interface TruckService {
    void addTruck(TruckRequest truck);

    TruckPositionResponse getTruckWithPosition(String plate);

    Truck getTruck(String plate);

    void getTruckPositionWithReport(String plate);
}
