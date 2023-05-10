package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "border_cross")
@Entity
@Data
public class BorderCrossEntity {

    @Id
    @Column(name = "vehicle_reg", nullable = false)
    private String vehicleReg;
}
