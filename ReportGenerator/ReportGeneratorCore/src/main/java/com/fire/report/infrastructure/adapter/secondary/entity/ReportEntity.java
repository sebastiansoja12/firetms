package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
@Entity
@Data
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "border_cross_vehicle_reg", referencedColumnName = "vehicle_reg")
    private BorderCrossEntity borderCross;

}
