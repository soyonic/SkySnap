package com.toyproject.skysnap.airport.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

// 외부 API 응답 단건 DTO
@Getter
@Setter
public class AirportExternalDto {
    @JsonProperty("airport_name")
    private String airportName;

    @JsonProperty("iata_code")
    private String iataCode;

    @JsonProperty("icao_code")
    private String icaoCode;

    private double latitude;
    private double longitude;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("city_iata_code")
    private String cityIataCode;

}
