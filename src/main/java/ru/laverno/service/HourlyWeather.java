package ru.laverno.service;

import org.springframework.stereotype.Service;
import ru.laverno.config.WeatherAPIConfig;

@Service
public class HourlyWeather {

    private final WeatherAPIConfig weatherConfig;

    public HourlyWeather(WeatherAPIConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public String getHourlyWeather() {
        return weatherConfig.getKey();
    }
}
