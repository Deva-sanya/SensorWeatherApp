package com.example.SensorWeatherApp.services;

import com.example.SensorWeatherApp.models.Measurement;
import com.example.SensorWeatherApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAllMeasurement() {
        return measurementRepository.findAll();
    }

    public int findRainyDays() {
        List<Measurement> measurements = measurementRepository.findAll();
        int count = 0;
        for (int i = 0; i < measurements.size(); i++) {
            if (measurements.get(i).isRaining() == true) {
                count++;
            }
        }
        return count;
    }

    @Transactional
    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
