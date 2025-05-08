package com.toyproject.skysnap.airport.loader;

import com.toyproject.skysnap.airport.dto.AirportCsvDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;


// CSV 로딩 및 맵 저장
@Component
public class AirportDataLoader {
    private final Map<String, List<AirportCsvDto>> cityAirportMap = new HashMap<>();

    @PostConstruct
    public void loadCsv() throws IOException    {

        InputStream is = getClass().getResourceAsStream("/airports.csv");
        Reader in = new InputStreamReader(is);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);

        for (CSVRecord record : records) {
            try {
                String name = record.get(3).trim();
                String municipality = record.get(10).trim();
                String isoCountry = record.get(8).trim();
                String iataCode = record.get(13).trim();
                String icaoCode = record.get(11).trim();
                double latitude = parseDouble(record.get(4));
                double longitude = parseDouble(record.get(5));

                if (municipality.isEmpty()) continue;

                AirportCsvDto dto = new AirportCsvDto();
                dto.setName(name);
                dto.setMunicipality(municipality);
                dto.setIsoCountry(isoCountry);
                dto.setIataCode(iataCode);
                dto.setIcaoCode(icaoCode);
                dto.setLatitudeDeg(latitude);
                dto.setLongitudeDeg(longitude);

                if (dto.getName().toLowerCase().contains("airport")) { // "airport"가 이름에 포함된 경우만 추가
                    String cityKey = municipality.toLowerCase();
                    cityAirportMap.computeIfAbsent(cityKey, k -> new ArrayList<>()).add(dto);
                }

            } catch (Exception e) {
                System.out.println("⚠️ 파싱 중 오류 발생: " + e.getMessage());
            }
        }

        System.out.println("CSV 공항 데이터 로딩 완료. 도시 수: " + cityAirportMap.size());

    }
    public List<AirportCsvDto> findByCity(String city){

        return cityAirportMap.getOrDefault(city.toLowerCase(), Collections.emptyList());
    }

    private double parseDouble(String val){
        try {
            return Double.parseDouble(val.trim());
        } catch(Exception e) {
            return 0.0;
        }
    }
}
