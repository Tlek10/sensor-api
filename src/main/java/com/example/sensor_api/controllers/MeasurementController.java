package com.example.sensor_api.controllers;

import com.example.sensor_api.dto.MeasurementDTO;
import com.example.sensor_api.services.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    public ResponseEntity<?> addMeasurement(@Valid @RequestBody MeasurementDTO measurementDTO) {
        measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.ok("Measurement added successfully");
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getRainyDaysCount() {
        return ResponseEntity.ok(measurementService.getRainyDaysCount());
    }
}
