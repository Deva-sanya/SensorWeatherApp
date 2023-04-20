package com.example.SensorWeatherApp.util;

import com.example.SensorWeatherApp.models.Sensor;
import com.example.SensorWeatherApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        /*if (sensorRepository.findSensorByName(sensor.getName())
        errors.rejectValue("name", "", "Sensor with this name already exists");*/
    }
}
