package com.example.SensorWeatherApp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "Value of temperature should not be empty")
    @Range(min = -100, max = 100, message = "The temperature should be between -100 and 100 degrees.")
    double temperature;

    @NotNull(message = "This field should not be empty")
    boolean raining;

    @NotNull(message = "This field should not be empty")
    private SensorDTO sensor;


}
