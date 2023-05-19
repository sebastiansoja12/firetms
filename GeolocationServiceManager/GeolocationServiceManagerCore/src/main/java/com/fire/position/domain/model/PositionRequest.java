package com.fire.position.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionRequest {

    @Pattern(regexp = "[A-Z0-9]{6}", message= "Vehicle reg length should be 6 and have no special characters")
    private String vehicleReg;

    @NotNull
    private Coordinate coordinate;

    @Pattern(regexp = "[A-Z]{3}", message="Use iso alfa 3 codes!")
    private String country;
}