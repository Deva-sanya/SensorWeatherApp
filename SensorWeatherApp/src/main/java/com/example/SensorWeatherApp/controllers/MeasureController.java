package com.example.SensorWeatherApp.controllers;

import com.example.SensorWeatherApp.dto.MeasurementDTO;
import com.example.SensorWeatherApp.dto.MeasurementsResponse;
import com.example.SensorWeatherApp.models.Measurement;
import com.example.SensorWeatherApp.services.MeasurementService;
import com.example.SensorWeatherApp.util.MeasurementErrorResponse;
import com.example.SensorWeatherApp.util.MeasurementException;
import com.example.SensorWeatherApp.util.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static com.example.SensorWeatherApp.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/measurements")
public class MeasureController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasureController(MeasurementService measurementService,
                                  MeasurementValidator measurementValidator,
                                  ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurementService.findAllMeasurement().stream().filter(Measurement::isRaining).count();
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementService.findAllMeasurement().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<HttpStatus> registrationOfMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementService.saveMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
