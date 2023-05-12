package ru.laverno.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherNowInfo {

    @JsonProperty(value = "weather")
    private List<Weather> weather;

    @JsonProperty(value = "main")
    private Temperature temperature;

    @JsonProperty(value = "wind")
    private Wind wind;
}
