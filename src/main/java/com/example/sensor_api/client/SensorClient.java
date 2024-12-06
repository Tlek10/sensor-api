package com.example.sensor_api.client;

import com.example.sensor_api.dto.MeasurementDTO;
import com.example.sensor_api.dto.SensorDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class SensorClient {

    private final WebClient webClient;
    private String jwtToken;

    public SensorClient() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080") // URL вашего API
                .build();
    }

    // Установить JWT токен
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    // 1. Регистрация сенсора
    public void registerSensor(String sensorName) {
        try {
            webClient.post()
                    .uri("/sensors/registration")
                    .bodyValue(new SensorDTO(sensorName))
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(response -> System.out.println("Sensor registered: " + response))
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println("Error registering sensor: " + e.getMessage());
        }
    }

    // 2. Отправка измерений
    public void addMeasurement(double value, boolean raining, String sensorName) {
        try {
            MeasurementDTO measurement = new MeasurementDTO(value, raining, new SensorDTO(sensorName));

            webClient.post()
                    .uri("/measurements/add")
                    .header("Authorization", "Bearer " + jwtToken)
                    .bodyValue(measurement)
                    .retrieve()
                    .bodyToMono(String.class)
                    .doOnSuccess(response -> System.out.println("Measurement added: " + response))
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println("Error adding measurement: " + e.getMessage());
        }
    }

    // 3. Получение всех измерений
    public void getAllMeasurements() {
        try {
            webClient.get()
                    .uri("/measurements")
                    .header("Authorization", "Bearer " + jwtToken)
                    .retrieve()
                    .bodyToFlux(MeasurementDTO.class)
                    .doOnNext(measurement -> System.out.println("Measurement: " + measurement))
                    .blockLast();
        } catch (WebClientResponseException e) {
            System.err.println("Error fetching measurements: " + e.getMessage());
        }
    }
}
