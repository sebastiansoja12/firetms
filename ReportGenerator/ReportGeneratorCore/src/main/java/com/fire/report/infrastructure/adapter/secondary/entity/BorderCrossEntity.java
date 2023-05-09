package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bordercross")
@Entity
public class BorderCrossEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "vehicleReg", nullable = false)
    private String vehicleReg;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "events_id", referencedColumnName = "id")
    private List<EventEntity> events;
}
