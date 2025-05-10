package com.toyproject.skysnap.airport.dto;

import lombok.Data;

// CSV 파싱용
@Data
public class AirportCsvDto {
    private String name; // 공항 이름
    private String municipality; // 공항 위치한 도시명
    private String isoCountry; // 국가코드
    private String iataCode; // IATA 코드
    private String icaoCode; // ICAO 코드
    private double latitudeDeg; // 위도
    private double longitudeDeg; // 경도
}
