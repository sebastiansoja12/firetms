package com.fire.truck.dto;

import lombok.Data;

@Data
public class TruckResponseDto {
    private String brand;

    private String model;

    private Double weight;

    private Double length;
}
