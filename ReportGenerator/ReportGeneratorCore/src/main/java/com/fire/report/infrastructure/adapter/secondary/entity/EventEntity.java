package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "eventTimeStamp", nullable = false)
    private Instant eventTimeStamp;

    @Column(name = "countryOut", nullable = false)
    private String countryOut;

    @Column(name = "countryIn", nullable = false)
    private String countryIn;

}
