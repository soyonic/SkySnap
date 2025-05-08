package com.toyproject.skysnap.airport.controller;


import com.toyproject.skysnap.airport.dto.AirportDto;
import com.toyproject.skysnap.airport.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<AirportDto>> searchAirports(@RequestParam String city){
        List<AirportDto> airports = airportService.getAirportByCity(city);
        if (airports.isEmpty()) {
            System.out.println("No airports found for city: " + city);
        } else {
            System.out.println("공항 검색 결과 (" + city + "): " + airports);
        }

        return ResponseEntity.ok(airports);
    }
}
