package com.fire.position.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinate {

    private Double longitude;

    private Double latitude;
}
