package com.fire.geocoding.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinate {
    private Double longitude;
    private Double latitude;
}
