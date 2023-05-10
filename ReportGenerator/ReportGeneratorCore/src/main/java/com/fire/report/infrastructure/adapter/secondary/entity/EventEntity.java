package com.fire.report.infrastructure.adapter.secondary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
@Entity
@Data
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "event_time_stamp", nullable = false)
    private Instant eventTimeStamp;

    @Column(name = "country_out", nullable = false)
    private String countryOut;

    @Column(name = "country_in", nullable = false)
    private String countryIn;

}
