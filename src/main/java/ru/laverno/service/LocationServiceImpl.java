package ru.laverno.service;

import org.springframework.stereotype.Service;
import ru.laverno.config.WeatherAPIConfig;
import ru.laverno.dto.LocationDTO;
import ru.laverno.entity.Location;
import ru.laverno.repository.LocationRepository;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class LocationServiceImpl extends BasicService implements LocationService {

    private final WeatherAPIConfig weatherConfig;

    private final LocationRepository repository;

    public LocationServiceImpl(WeatherAPIConfig weatherConfig, LocationRepository repository) {
        this.weatherConfig = weatherConfig;
        this.repository = repository;
    }

    @Override
    public Location getLocationByChatId(long chatId) {
        return repository.findLocationByChatId(chatId).orElse(null);
    }

    @Override
    public Location setLocation(long chatId, String city) {
        try {
            final var url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=5&appid=" + weatherConfig.getKey());
            final var response = sendRequest(url, LocationDTO[].class);
            Location result;

            final var location = getLocationByChatId(chatId);

            if (location != null) {
                result = new Location(location.getId(), response[0], location.getChatId());
            } else {
                result = new Location(response[0], chatId);
            }
            
            repository.save(result);

            return result;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
