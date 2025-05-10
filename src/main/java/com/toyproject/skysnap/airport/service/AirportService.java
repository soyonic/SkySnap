package com.toyproject.skysnap.airport.service;

import com.toyproject.skysnap.airport.dto.AirportDto;
import com.toyproject.skysnap.airport.external.response.AirportApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final RestTemplate restTemplate;

    @Value("${API_KEY}")
    private String apiKey;
    public AirportService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public List<AirportDto> getAirportsByCity(String city){
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = "https://api.aviationstack.com/v1/airports?access_key=" + apiKey + "&search=" + encodedCity;


        // 외부 API 호출
        AirportApiResponse response = restTemplate.getForObject(url, AirportApiResponse.class);

        if(response == null || response.getData() == null) {
            return Collections.emptyList();
        }

        return response.getData().stream()
                .map(e -> new AirportDto(
                        e.getAirportName(),
                        e.getIataCode(),
                        e.getIcaoCode(),
                        e.getLatitude(),
                        e.getLongitude(),
                        e.getCountryName(),
                        e.getCityIataCode()

                ))
                .collect(Collectors.toList());
    }
}
