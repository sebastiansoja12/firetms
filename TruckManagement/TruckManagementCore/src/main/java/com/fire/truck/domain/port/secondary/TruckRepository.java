package com.fire.truck.domain.port.secondary;

import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.model.Truck;

public interface TruckRepository {
    Truck findByPlate(String plate);

    void saveTruck(TruckRequest truck);
}
