package com.fire.truck.infrastructure.adapter.secondary;

import com.fire.truck.infrastructure.adapter.secondary.entity.TruckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckReadRepository extends JpaRepository<TruckEntity, String> {

    Optional<TruckEntity> findByPlate(String plate);
}
