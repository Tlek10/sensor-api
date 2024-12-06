package com.example.sensor_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class MeasurementDTO {
    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotNull(message = "Raining status cannot be null")
    private Boolean raining;

    @NotNull(message = "Sensor information is required")
    private SensorDTO sensor;
    public MeasurementDTO(@NotNull(message = "Value cannot be null") Double value,
                          @NotNull(message = "Raining status cannot be null") Boolean raining,
                          @NotNull(message = "Sensor information is required") SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }
}
