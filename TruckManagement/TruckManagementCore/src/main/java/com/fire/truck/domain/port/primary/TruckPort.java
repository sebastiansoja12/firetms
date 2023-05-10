package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;

import java.util.List;

public interface TruckPort {
    void addTruck(List<TruckRequest> truckRequest);

    TruckPositionResponse getTruckWithPosition(String plate);

    Truck getTruckByPlate(String plate);

    void getTruckPositionWithReport(String plate);
}
