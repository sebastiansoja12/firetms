package com.fire.truck.domain.port.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.service.TruckService;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
public class TruckPortImpl implements TruckPort {

    private final TruckService truckService;


    @Override
    public void addTruck(List<TruckRequest> truckRequests) {
        truckRequests.stream()
                .filter(TruckRequest::isNotNull)
                .forEach(truckService::addTruck);
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return truckService.getTruck(plate);
    }
}
