package com.example.sensor_api.controllers;

import com.example.sensor_api.dto.SensorDTO;
import com.example.sensor_api.services.SensorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerSensor(@Valid @RequestBody SensorDTO sensorDTO) {
        sensorService.registerSensor(sensorDTO);
        return ResponseEntity.ok("Sensor registered successfully");
    }
}