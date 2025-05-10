package com.toyproject.skysnap.airport.service;

import com.toyproject.skysnap.airport.dto.AirportResponseDto;
import java.util.List;


// 공항 검색 비즈니스 로직을 정의 - 이렇게 하는 이유??
public interface AirportService {
    List<AirportResponseDto> findAirportsByCity(String cityName);
}
