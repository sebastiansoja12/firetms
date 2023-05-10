package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "border_cross")
@Entity
@Data
public class BorderCrossEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "vehicle_reg", nullable = false)
    private String vehicleReg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "event_id", referencedColumnName = "id")
    private EventEntity event;
}
