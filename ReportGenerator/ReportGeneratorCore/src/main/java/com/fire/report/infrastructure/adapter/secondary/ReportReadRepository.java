package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.infrastructure.adapter.secondary.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReportReadRepository extends JpaRepository<ReportEntity, UUID> {

    Optional<ReportEntity> findById(UUID id);

    @Query("SELECT DISTINCT r FROM ReportEntity r JOIN r.borderCross b WHERE b.vehicleReg = :vehicleReg")
    Optional<ReportEntity> findByVehicleRegWithEvents(@Param("vehicleReg") String vehicleReg);
}
