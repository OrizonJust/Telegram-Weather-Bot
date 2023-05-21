package ru.laverno.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyWeatherAPIResponse {

    @JsonProperty(value = "cnt")
    private String count;

    @JsonProperty(value = "list")
    private List<WeatherNowInfo> hourlyWeatherList;
}
