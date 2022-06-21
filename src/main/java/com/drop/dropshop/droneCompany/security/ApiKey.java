package com.drop.dropshop.droneCompany.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
public class ApiKey {
    @Value("${gugsecheong-api-key}")
    private String gugsecheongApiKey;
    @Value("${naver-geo-api-key}")
    private String naverGeoApiKey;
    @Value("${naver-geo-api-secret-key}")
    private String naverGeoApiSecretKey;
    @Value("${open-weather-map-api-key}")
    private String openWeatherMapApiKey;
}
