package com.toyproject.skysnap.airport.mapper;

import com.toyproject.skysnap.airport.dto.AirportCsvDto;
import com.toyproject.skysnap.airport.dto.AirportDto;

// DTO 변환
public class AirportMapper {
    public static AirportDto toDto(AirportCsvDto csvDto){
        return AirportDto.builder()
                .name(csvDto.getName())
                .country(csvDto.getIsoCountry())
                .iata(csvDto.getIataCode())
                .icao(csvDto.getIcaoCode())
                .lat(csvDto.getLatitudeDeg())
                .lon(csvDto.getLongitudeDeg())
                .build();
    }
}
