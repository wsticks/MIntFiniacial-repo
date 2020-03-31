package com.williams.mint.mintfinancial.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix = "card")
@Data
@NoArgsConstructor
public class CardConfiguration {

    String baseUrl;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
