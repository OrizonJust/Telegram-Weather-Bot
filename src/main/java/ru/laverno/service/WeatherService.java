package ru.laverno.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.laverno.Const;
import ru.laverno.config.WeatherAPIConfig;
import ru.laverno.model.Location;
import ru.laverno.model.Weather;
import ru.laverno.model.WeatherNowInfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherAPIConfig weatherConfig;

    public WeatherService(WeatherAPIConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
    }

    public Location setLocation(String city) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final var url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=5&appid=" + weatherConfig.getKey());
            final var response = restTemplate.getForEntity(url.toURI(), Location[].class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody()[0];
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getWeather(Location location) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final var url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + location.getLat() + "&lon=" + location.getLon() + "&appid=" + weatherConfig.getKey() + "&lang=ru&units=metric");
            final var response = restTemplate.getForEntity(url.toURI(), WeatherNowInfo.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                final var body = response.getBody();
                final var weather = body.getWeather();
                final var temperature = body.getTemperature();
                final var wind = body.getWind();
                return String.format(Const.TEMPERATURE_MESSAGE, location.getLocalName().getRuName(), convertWeatherIntoDescription(weather), temperature.getTemp(), temperature.getFeelsLike(), wind.getSpeed());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
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
