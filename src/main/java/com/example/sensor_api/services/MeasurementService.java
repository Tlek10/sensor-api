package com.example.sensor_api.services;

import com.example.sensor_api.dto.MeasurementDTO;
import com.example.sensor_api.dto.SensorDTO;
import com.example.sensor_api.entities.Measurement;
import com.example.sensor_api.entities.Sensor;
import com.example.sensor_api.repository.MeasurementRepository;
import com.example.sensor_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public void addMeasurement(MeasurementDTO measurementDTO) {
        Sensor sensor = sensorRepository.findByName(measurementDTO.getSensor().getName())
                .orElseThrow(() -> new IllegalArgumentException("Sensor not found"));

        Measurement measurement = Measurement.builder()
                .value(measurementDTO.getValue())
                .raining(measurementDTO.getRaining())
                .sensor(sensor)
                .timestamp(LocalDateTime.now())
                .build();

        measurementRepository.save(measurement);
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementRepository.findAll().stream()
                .map(measurement -> MeasurementDTO.builder()
                        .value(measurement.getValue())
                        .raining(measurement.getRaining())
                        .sensor(SensorDTO.builder()
                                .name(measurement.getSensor().getName())
                                .build())
                        .build())
                .toList();
    }

    public Long getRainyDaysCount() {
        return measurementRepository.countRainyDays();
    }
}
