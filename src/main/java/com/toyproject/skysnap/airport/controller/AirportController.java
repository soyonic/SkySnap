package com.toyproject.skysnap.airport.controller;


import com.toyproject.skysnap.airport.dto.AirportResponseDto;
import com.toyproject.skysnap.airport.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 사용자 요청(/api/airports)을 받아 공항 정보를 반환하는 REST API 컨트롤러
@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<AirportResponseDto>> getAirports(@RequestParam String city) {
        List<AirportResponseDto> airports = airportService.findAirportsByCity(city);
        return ResponseEntity.ok(airports);

    }
}
