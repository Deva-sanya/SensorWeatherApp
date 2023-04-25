package com.example.SensorWeatherApp.services;

import com.example.SensorWeatherApp.models.Sensor;
import com.example.SensorWeatherApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }

    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void saveSensor(Sensor sensor) {

        sensorRepository.save(sensor);
    }

}
