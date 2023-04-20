package com.example.SensorWeatherApp.controllers;

import com.example.SensorWeatherApp.models.Measurement;
import com.example.SensorWeatherApp.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/measurements")
public class MeasureController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasureController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public String allMeasurement() {
        measurementService.findAllMeasurement();
        return "/books/addBook";
    }

    @GetMapping("/rainyDaysCount")
    public String findRainyDays(@ModelAttribute("measurements") List<Measurement> measurements) {
        measurementService.findRainyDays(measurements);
        return "/books/addBook";
    }

    @GetMapping("/newMeasurement")
    public String newMeasurement(@ModelAttribute("measurement") Measurement measurement) {
        return "/books/addBook";
    }

    @PostMapping("/add")
    public String registrationOfMeasurement(@ModelAttribute("measurement") Measurement measurement) {
        measurementService.saveMeasurement(measurement);
        return "redirect:/books";
    }
}
