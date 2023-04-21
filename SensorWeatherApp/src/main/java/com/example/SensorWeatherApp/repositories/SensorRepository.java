package com.example.SensorWeatherApp.repositories;

import com.example.SensorWeatherApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findSensorByName(String name);
}
