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
        String url = String.format("https://airportdb.io/api/v1/airport/%s?apiToken=%s", icao, apiToken);

        try {
            // ❌ 더 이상 헤더 필요 없음
            ResponseEntity<AirportResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null, // 헤더 필요 X
                    AirportResponseDto.class
            );

            // 빈 응답을 위해 로그 출력
            System.out.println("API 응답: " + response.getBody());

            return response.getBody();

        } catch (Exception e) {
            // 빈 응답을 위해 로그 출력
            System.err.printf("ICAO 코드 %s 조회 실패: %s%n", icao, e.getMessage());
            e.printStackTrace(); // 이거 꼭 있어야 전체 예외 로그 보여줌!
            return null;
        }
    }
}
