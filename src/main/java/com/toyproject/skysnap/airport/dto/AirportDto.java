package com.toyproject.skysnap.airport.dto;

import lombok.Builder;
import lombok.Data;

// 사용자 응답용

@Data
@Builder
public class AirportDto {

        private String name;
        private String country;
        private String iata;
        private String icao;
        private double lat;
        private double lon;

}
