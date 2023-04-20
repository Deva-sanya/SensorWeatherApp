package com.example.SensorWeatherApp.repositories;

import com.example.SensorWeatherApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findSensorByNameStartsWith(String name);
}
