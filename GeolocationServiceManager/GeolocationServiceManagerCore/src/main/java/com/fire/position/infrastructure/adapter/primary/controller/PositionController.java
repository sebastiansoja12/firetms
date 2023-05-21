package com.fire.position.infrastructure.adapter.primary.controller;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.PositionRequest;
import com.fire.position.domain.model.TruckPosition;
import com.fire.position.domain.port.primary.PositionPort;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapper;
import com.fire.position.infrastructure.adapter.primary.mapper.PositionRequestMapperImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
@AllArgsConstructor
@Validated
public class PositionController {

    private final PositionPort positionPort;

    private final PositionRequestMapper positionRequestMapper = new PositionRequestMapperImpl();


    @PostMapping
    public ResponseEntity<?> insertPosition(@RequestBody @Valid PositionRequest positionRequest) {
        final Position position = positionRequestMapper.map(positionRequest);
        final Position positionResponse = positionPort.insertPosition(position);
        return ResponseEntity.ok().body(positionResponse);
    }

    @GetMapping("/{vehicleReg}/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getPositionForTruck(@PathVariable @Pattern(regexp = "[A-Z0-9]{6}") String vehicleReg,
             @PathVariable @Valid int pageNumber, @PathVariable @Valid int pageSize) {
        final TruckPosition truckPosition = positionPort.getVehiclePosition(vehicleReg, pageNumber, pageSize);
        return ResponseEntity.ok().body(truckPosition);
    }

}

