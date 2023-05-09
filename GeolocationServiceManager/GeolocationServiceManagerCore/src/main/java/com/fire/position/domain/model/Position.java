package com.fire.position.domain.model;

import lombok.Data;

@Data
public class Position {

    private Coordinate coordinate;
    private String country;
    private String timestamp;

}