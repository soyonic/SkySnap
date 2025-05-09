package com.toyproject.skysnap.airport.external.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirportApiResponse {
    private List<AirportExternalDto> data;

}
