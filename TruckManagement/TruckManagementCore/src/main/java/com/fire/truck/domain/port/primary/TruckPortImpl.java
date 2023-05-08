package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.exception.EmptyBrandException;
import com.fire.truck.domain.exception.EmptyTruckRequestException;
import com.fire.truck.domain.model.Truck;
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
    public Truck getTruck(String plate) {
        return truckService.getTruckByPlate(plate);
    }

    private boolean isNotNull(TruckRequest truckRequest) {
        return !Objects.isNull(truckRequest);
    }

    private void validateRequest(TruckRequest truckRequest) {
        if (truckRequest == null) {
            throw new EmptyTruckRequestException("Track request is null");
        }
        if (truckRequest.getBrand() == null) {
            throw new EmptyBrandException("Brand is null");
        }
        if (truckRequest.getModel() == null) {
            throw new EmptyTruckRequestException("Model is null");
        }
    }
}
