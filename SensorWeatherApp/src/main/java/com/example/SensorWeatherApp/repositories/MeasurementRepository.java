package com.example.SensorWeatherApp.repositories;

import com.example.SensorWeatherApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
