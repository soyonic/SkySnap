package com.toyproject.skysnap.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// country 값이 2개여서 dto로 만들기
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

        private String code;
        private String name;
}
