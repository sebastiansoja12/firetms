package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.service.TruckService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class TruckPortImpl implements TruckPort {

    private final TruckService truckService;


    @Override
    public void addTruck(List<TruckRequest> truckRequests) {
        truckRequests.stream()
                .filter(this::isNotNull)
                .forEach(truckService::addTruck);
    }

    @Override
    public TruckPositionResponse getTruckWithPosition(String plate) {
        return truckService.getTruckWithPosition(plate);
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return truckService.getTruck(plate);
    }

    @Override
    public void getTruckPositionWithReport(String plate) {
        truckService.getTruckPositionWithReport(plate);
    }

    private boolean isNotNull(TruckRequest truckRequest) {
        return !Objects.isNull(truckRequest);
    }
}
