package com.example.SensorWeatherApp.services;

import com.example.SensorWeatherApp.models.Measurement;
import com.example.SensorWeatherApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();
        String createMeasurement = "http://localhost:8080/measurements/add";

        for (int i = 0; i < 10; i++) {
            double randomTemperature = random.nextDouble();
            boolean randomRaining = random.nextBoolean();

            Measurement measurement1 = new Measurement(randomTemperature, randomRaining);
            ResponseEntity<Measurement> result = restTemplate.postForEntity(createMeasurement, measurement1, Measurement.class);

            assertNotNull(result.getBody());
            assertNotNull(result.getBody().getTemperature());
            assertNotNull(result.getBody().isRaining());
        }
    }
}
