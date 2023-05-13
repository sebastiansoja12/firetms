package com.fire.position.infrastructure.adapter.secondary;

import com.fire.position.infrastructure.adapter.secondary.entity.PositionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PositionReadRepository extends JpaRepository<PositionEntity, Long> {
    List<PositionEntity> findAllByVehicleReg(String vehicleReg, Sort sort);

    List<PositionEntity> findAllByVehicleReg(String vehicleReg, Pageable pageable);

    boolean existsByVehicleReg(String vehicleReg);

    Optional<PositionEntity> findDistinctFirstByVehicleRegOrderByIdDesc(String plate);
}
