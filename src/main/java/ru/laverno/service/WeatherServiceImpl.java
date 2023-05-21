package ru.laverno.service;

import org.springframework.stereotype.Service;
import ru.laverno.Const;
import ru.laverno.config.WeatherAPIConfig;
import ru.laverno.entity.Location;
import ru.laverno.model.HourlyWeatherAPIResponse;
import ru.laverno.model.Weather;
import ru.laverno.model.WeatherNowInfo;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class WeatherServiceImpl extends BasicService implements WeatherService {

    private final WeatherAPIConfig weatherConfig;

    public WeatherServiceImpl(WeatherAPIConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public String getWeather(Location location) {
        try {
            final var url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + location.getLat() + "&lon=" + location.getLon() + "&appid=" + weatherConfig.getKey() + "&lang=ru&units=metric");
            final var response = sendRequest(url, WeatherNowInfo.class);

            final var weather = response.getWeather();
            final var temperature = response.getTemperature();
            final var wind = response.getWind();
            return String.format(Const.TEMPERATURE_MESSAGE, location.getLocalName(), convertWeatherIntoDescription(weather), temperature.getTemp(), temperature.getFeelsLike(), wind.getSpeed());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getHourlyWeather(Location location) {
        try {
            final var url = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + location.getLat() + "&lon=" + location.getLon() + "&appid=" + weatherConfig.getKey() + "&lang=ru&units=metric");
            final var response = sendRequest(url, HourlyWeatherAPIResponse.class);

            final var sb = new StringBuilder();
            sb.append("Погода сейчас:");
            sb.append("\n");
            sb.append(getWeather(location));
            sb.append("\n");

            for (int i = 1; i < 5; i++) {
                final var weather = response.getHourlyWeatherList().get(i);
                final var time = weather.getDateTime();
                sb.append(String.format("Дата: %d.%d.%d Время: %d часов", time.getDayOfMonth(), time.getMonthValue(), time.getYear(), time.getHour()));
                sb.append("\n");
                sb.append(String.format(Const.TEMPERATURE_MESSAGE, location.getLocalName(), convertWeatherIntoDescription(weather.getWeather()), weather.getTemperature().getTemp(), weather.getTemperature().getFeelsLike(), weather.getWind().getSpeed()));
                sb.append("\n");
            }

            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String convertWeatherIntoDescription(List<Weather> weathers) {
        final var sb = new StringBuilder();

        for (var weather : weathers) {
            sb.append(weather.getWeatherDescription());
            sb.append(",");
        }

        return sb.toString();
    }
}
