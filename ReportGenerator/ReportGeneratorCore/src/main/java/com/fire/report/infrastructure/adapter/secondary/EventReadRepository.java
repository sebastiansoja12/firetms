package com.fire.report.infrastructure.adapter.secondary;

import com.fire.report.infrastructure.adapter.secondary.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventReadRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findById(Long id);
}
