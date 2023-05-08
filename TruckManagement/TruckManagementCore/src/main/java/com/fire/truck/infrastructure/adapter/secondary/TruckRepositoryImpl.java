package com.fire.truck.infrastructure.adapter.secondary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.secondary.TruckRepository;
import com.fire.truck.infrastructure.adapter.secondary.entity.TruckEntity;
import com.fire.truck.infrastructure.adapter.secondary.exception.TruckNotFoundException;
import com.fire.truck.infrastructure.adapter.secondary.mapper.TruckModelMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TruckRepositoryImpl implements TruckRepository {

    private final TruckReadRepository truckReadRepository;

    private final TruckModelMapper truckModelMapper;

    @Override
    public Truck findByPlate(String plate) {
        return truckReadRepository.findByPlate(plate).map(truckModelMapper::map).orElseThrow(
                () -> new TruckNotFoundException("Truck with plate: " + plate + " was not found!")
        );
    }

    @Override
    public void saveTruck(TruckRequest truck) {
        final TruckEntity truckEntity = truckModelMapper.map(truck);
        truckReadRepository.save(truckEntity);
    }
}
