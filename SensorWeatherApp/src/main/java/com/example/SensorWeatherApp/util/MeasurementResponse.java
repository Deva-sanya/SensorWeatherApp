package com.example.SensorWeatherApp.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementResponse {

    private String message;
    private long timestamp;

    public MeasurementResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
