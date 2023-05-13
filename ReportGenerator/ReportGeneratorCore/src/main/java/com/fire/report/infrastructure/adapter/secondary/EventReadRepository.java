package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventReadRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT DISTINCT r FROM EventEntity r WHERE r.vehicleReg = :vehicleReg")
    Page<EventEntity> findDistinctEventTimestampByVehicleReg(@Param("vehicleReg") String vehicleReg, Pageable pageable);

    @Query("SELECT e FROM EventEntity e WHERE e.vehicleReg = :vehicleReg AND e.eventTimeStamp BETWEEN :dateFrom AND :dateTo" +
            " ORDER BY e.eventTimeStamp ASC")
    List<EventEntity> findEventsByVehicleRegAndDates(@Param("vehicleReg") String vehicleReg,
                                                     @Param("dateFrom") Instant dateFrom,
                                                     @Param("dateTo") Instant dateTo);


}
