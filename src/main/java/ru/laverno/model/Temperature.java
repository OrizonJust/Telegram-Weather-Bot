package ru.laverno.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {

    @JsonProperty(value = "temp")
    private double temp;

    @JsonProperty(value = "feels_like")
    private double feelsLike;

    @JsonProperty(value = "temp_min")
    private double tempMin;

    @JsonProperty(value = "temp_max")
    private double tempMax;
}
