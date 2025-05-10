package com.toyproject.skysnap.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


// 사용자에게 줄 DTO
@Getter
@Setter
@AllArgsConstructor
public class AirportDto {
    private String airportName;
    private String iataCode;
    private String icaoCode;
    private double latitude;
    private double longitude;
    private String countryName;
    private String cityIataCode;
}