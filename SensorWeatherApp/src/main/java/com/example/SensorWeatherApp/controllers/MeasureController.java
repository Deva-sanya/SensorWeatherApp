package com.example.SensorWeatherApp.controllers;

import com.example.SensorWeatherApp.models.Measurement;
import com.example.SensorWeatherApp.services.MeasurementService;
import com.example.SensorWeatherApp.util.MeasurementNotAddException;
import com.example.SensorWeatherApp.util.MeasurementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Measurement> allMeasurement() {
        return measurementService.findAllMeasurement();
    }

    @GetMapping("/rainyDaysCount")
    public int findRainyDays() {
        return measurementService.findRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registrationOfMeasurement(@RequestBody @Valid Measurement measurement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new MeasurementNotAddException(errorMsg.toString());
        }
        measurementService.saveMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<MeasurementResponse> handleException(MeasurementNotAddException e) {
        MeasurementResponse response = new MeasurementResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
