package com.example.SensorWeatherApp.services;

import com.example.SensorWeatherApp.models.Sensor;
import com.example.SensorWeatherApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findSensorByName(String name) {
        return sensorRepository.findSensorByNameStartsWith(name);
    }

    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

}
