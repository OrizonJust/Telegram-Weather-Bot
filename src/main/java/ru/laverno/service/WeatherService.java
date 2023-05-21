package ru.laverno.service;

import ru.laverno.entity.Location;

public interface WeatherService {

    String getWeather(Location location);

    String getHourlyWeather(Location location);
}
