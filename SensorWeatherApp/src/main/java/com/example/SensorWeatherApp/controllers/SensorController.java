package com.example.SensorWeatherApp.controllers;

import com.example.SensorWeatherApp.models.Sensor;
import com.example.SensorWeatherApp.services.SensorService;
import com.example.SensorWeatherApp.util.SensorErrorResponse;
import com.example.SensorWeatherApp.util.SensorNotRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> allSensors() {
        return sensorService.findAllSensors();
    }

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<HttpStatus> registrationOfSensor(@RequestBody @Valid Sensor sensor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotRegistrationException(errorMsg.toString());
        }
        sensorService.saveSensor(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegistrationException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
