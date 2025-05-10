package com.toyproject.skysnap.airport.client;


import com.toyproject.skysnap.airport.dto.AirportResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// ICAO 코드 이용해 AirportDB api 호출
@Component
public class AirportDbClient {
    @Value("${airportdb.api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public AirportDbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AirportResponseDto getAirportByIcao(String icao){
        String url = String.format("https://airportdb.io/api/v1/airport/icao/%s", icao);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken); // Authorization: Bearer {token}
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<AirportResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    AirportResponseDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
