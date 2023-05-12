package ru.laverno.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeatherAPIConfig {

    @Value("${weather.key}")
    private String key;
}
