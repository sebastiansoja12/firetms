package com.fire.truck.infrastructure.adapter.primary;

import com.fire.truck.domain.model.Truck;
import com.fire.truck.domain.model.TruckPositionResponse;
import com.fire.truck.domain.model.TruckRequest;
import com.fire.truck.domain.port.primary.TruckPort;
import com.fire.truck.dto.TruckRequestDto;
import com.fire.truck.dto.TruckResponseDto;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckRequestMapper;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckRequestMapperImpl;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckResponseMapper;
import com.fire.truck.infrastructure.adapter.primary.mapper.TruckResponseMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api/trucks")
@AllArgsConstructor
public class TruckController {

    private final TruckPort truckPort;

    private final TruckRequestMapper truckRequestMapper = new TruckRequestMapperImpl();

    private final TruckResponseMapper truckResponseMapper = new TruckResponseMapperImpl();


    @PostMapping
    public ResponseEntity<?> addTruck(@RequestBody List<TruckRequestDto> truckRequestDto) {
        final List<TruckRequest> truckRequest = truckRequestMapper.map(truckRequestDto);
        truckPort.addTruck(truckRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(truckRequest);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<?> getTruck(@PathVariable String plate) {
        final Truck truck =  truckPort.getTruckByPlate(plate);
        final TruckResponseDto truckResponse = truckResponseMapper.map(truck);
        return ResponseEntity.status(HttpStatus.FOUND).body(truckResponse);
    }

    @GetMapping("/position/{plate}")
    public ResponseEntity<?> getTrucksPosition(@PathVariable String plate) {
        final TruckPositionResponse truckResponse = truckPort.getTruckWithPosition(plate);
        return ResponseEntity.status(HttpStatus.FOUND).body(truckResponse);
    }

    @GetMapping("/report/{plate}")
    public ResponseEntity<?> getTruckPositionWithReport(@PathVariable String plate) {
        truckPort.getTruckPositionWithReport(plate);
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

}
