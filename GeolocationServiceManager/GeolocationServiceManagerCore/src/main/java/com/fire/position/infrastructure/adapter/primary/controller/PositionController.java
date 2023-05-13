package com.fire.position.infrastructure.adapter.primary.controller;

import com.fire.position.domain.model.Position;
import com.fire.position.domain.model.TruckPosition;
import com.fire.position.domain.port.primary.PositionPort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/positions")
@AllArgsConstructor
public class PositionController {

    private final PositionPort positionPort;


    @PostMapping
    public ResponseEntity<?> insertPosition(@RequestBody Position position) {
        positionPort.insertPosition(position);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{vehicleReg}/{pageNumber}/{pageSize}")
    public ResponseEntity<?> getPositionForTruck(@PathVariable String vehicleReg,
                                                 @PathVariable int pageNumber, @PathVariable int pageSize) {
        final TruckPosition truckPosition = positionPort.getVehiclePosition(vehicleReg, pageNumber, pageSize);
        return ResponseEntity.ok().body(truckPosition);
    }

}
