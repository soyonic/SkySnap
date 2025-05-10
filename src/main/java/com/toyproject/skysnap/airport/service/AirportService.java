package com.toyproject.skysnap.airport.service;

import com.toyproject.skysnap.airport.dto.AirportCsvDto;
import com.toyproject.skysnap.airport.dto.AirportDto;
import com.toyproject.skysnap.airport.loader.AirportDataLoader;
import com.toyproject.skysnap.airport.mapper.AirportMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportDataLoader loader;

    public AirportService(AirportDataLoader loader) {
        this.loader = loader;
    }

    public List<AirportDto> getAirportByCity(String cityName){
        List<AirportCsvDto> raw = loader.findByCity(cityName);

        return raw.stream()
                .filter(dto -> dto.getIataCode() != null && !dto.getIataCode().isEmpty())
                .map(AirportMapper::toDto)
                .collect(Collectors.toList());
//        return loader.findByCity(cityName).stream()
//                .filter(dto -> dto.getIataCode() != null && !dto.getIataCode().isEmpty())
//                .map(AirportMapper::toDto)
//                .collect(Collectors.toList());
    }
}
