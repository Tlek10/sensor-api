package com.example.sensor_api.exception;

public class SensorAlreadyExistsException extends RuntimeException {
    public SensorAlreadyExistsException(String message) {
        super(message);
    }
}
