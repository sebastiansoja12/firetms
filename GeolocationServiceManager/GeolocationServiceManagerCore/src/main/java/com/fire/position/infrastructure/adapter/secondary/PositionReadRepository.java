package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionReadRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findAllByVehicleReg(String vehicleReg, Sort sort);

    boolean existsByVehicleReg(String vehicleReg);
}
