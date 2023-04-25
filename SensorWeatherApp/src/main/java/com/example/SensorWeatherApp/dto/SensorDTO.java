package com.example.SensorWeatherApp.dto;

import com.example.SensorWeatherApp.models.Measurement;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Name of sensor should not be empty")
    @Size(min = 3, max = 30, message = "The name of the sensor should be between 3 and 30 characters long.")
    private String name;

    private List<Measurement> measurements;
}
