package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")
@Entity
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "borderCrossEntity_id", referencedColumnName = "id")
    private List<BorderCrossEntity> borderCrossEntity;
}
