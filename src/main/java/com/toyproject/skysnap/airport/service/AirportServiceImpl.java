package com.toyproject.skysnap.airport.service;

import com.toyproject.skysnap.airport.client.AirportDbClient;
import com.toyproject.skysnap.airport.dto.AirportResponseDto;
import com.toyproject.skysnap.airport.parser.AirportsDatParser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


// 도시명으로 공항 정보를 조회 - 실질적 서비스
@Service
public class AirportServiceImpl implements AirportService{
    private final AirportsDatParser parser;
    private final AirportDbClient client;

    public AirportServiceImpl(AirportsDatParser parser, AirportDbClient client) {
        this.parser = parser;
        this.client = client;
    }

    @Override
    public List<AirportResponseDto> findAirportsByCity(String cityName) {
        List<String> icaoCodes = parser.findIcaoByCity(cityName);

        return icaoCodes.stream()
                .map(client::getAirportByIcao)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
