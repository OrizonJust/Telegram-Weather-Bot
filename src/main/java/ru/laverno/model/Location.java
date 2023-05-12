package ru.laverno.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "local_names")
    private LocalName localName;

    @JsonProperty(value = "lat")
    private double lat;

    @JsonProperty(value = "lon")
    private double lon;

    @JsonProperty(value = "country")
    private String country;
}
