package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;

import java.util.List;

public interface TruckPort {
    void addTruck(List<TruckRequest> truckRequest);

    Truck getTruck(String plate);
}
