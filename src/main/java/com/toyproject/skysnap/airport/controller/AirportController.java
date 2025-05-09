package com.toyproject.skysnap.airport.controller;

import com.toyproject.skysnap.airport.dto.AirportDto;
import com.toyproject.skysnap.airport.service.AirportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDto> getAirports(@RequestParam String city) {
        return airportService.getAirportsByCity(city);
    }
}