package com.toyproject.skysnap.airport.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// HTTP 요청을 보내는데 사용
// 스프링에 빈으로 등록해 다른 클래스에서 주입받아 사용하게 함
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
