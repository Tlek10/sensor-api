package com.example.sensor_api.services;

import com.example.sensor_api.dto.SensorDTO;
import com.example.sensor_api.entities.Sensor;
import com.example.sensor_api.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public void registerSensor(SensorDTO sensorDTO) {
        if (sensorRepository.findByName(sensorDTO.getName()).isPresent()) {
            throw new IllegalArgumentException("Sensor with this name already exists");
        }

        Sensor sensor = Sensor.builder()
                .name(sensorDTO.getName())
                .build();

        sensorRepository.save(sensor);
    }
}

