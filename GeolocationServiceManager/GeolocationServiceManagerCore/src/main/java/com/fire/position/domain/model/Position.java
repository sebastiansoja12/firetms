package com.fire.position.domain.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Size(min=4, max = 6, message="Vehicle reg length should be between 4 and 6")
    private String vehicleReg;

    @NotNull
    private Coordinate coordinate;

    @Size(min = 3, max = 3, message = "Country code ISO Alpha 3 must have 3 characters")
    private String country;

    private String timestamp;

}
