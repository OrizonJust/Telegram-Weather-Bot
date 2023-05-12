package ru.laverno.service;

import org.springframework.stereotype.Service;
import ru.laverno.config.WeatherAPIConfig;
import ru.laverno.model.Location;

@Service
public class HourlyWeather {

    private final WeatherAPIConfig weatherConfig;

    public HourlyWeather(WeatherAPIConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public String getHourlyWeather(final Location location) {


        return weatherConfig.getKey();
    }
}
