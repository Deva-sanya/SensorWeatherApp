package com.example.SensorWeatherApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;

    public MeasurementsResponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
