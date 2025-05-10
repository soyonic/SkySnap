package com.toyproject.skysnap.airport.parser;


import com.toyproject.skysnap.airport.model.AirportInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// airports.dat 파일을 읽어 도시명-ICAO 코드 간 매핑 제공
@Component
public class AirportsDatParser {
    private final List<AirportInfo> airportList = new ArrayList<>();

    @PostConstruct
    public void init() throws IOException {
        Path path = Paths.get("src/main/resources/airports.dat");
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens.length >= 7) {
                    String name = stripQuotes(tokens[1]);
                    String city = stripQuotes(tokens[2]);
                    String country = stripQuotes(tokens[3]);
                    String iata = stripQuotes(tokens[4]);
                    String icao = stripQuotes(tokens[5]);

                    airportList.add(new AirportInfo(name, city, country, iata, icao));
                }
            }
        }
    }
    private String stripQuotes(String s){
        return s.replaceAll("^\"|\"$", "");

    }

    public List<String> findIcaoByCity(String cityName) {

        // 포스트맨 빈 응답 때문에 로그 찍어보기
        List<String> result = airportList.stream()
                .filter(a -> a.getCity().equalsIgnoreCase(cityName))
                .map(AirportInfo::getIcao)
                .filter(code -> code != null && !code.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("도시명: " + cityName + " → ICAO 코드 리스트: " + result);
        return result;
    }
}
