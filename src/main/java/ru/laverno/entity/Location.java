package ru.laverno.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.laverno.dto.LocationDTO;

import java.util.UUID;

@Getter
@Table(schema = "wbot", name = "w_t_location")
public class Location {

    @Id
    @Column(value = "l_id")
    private final UUID id;

    @Column(value = "l_name")
    private final String name;

    @Column(value = "l_local_name")
    private final String localName;

    @Column(value = "l_lon")
    private final double lon;

    @Column(value = "l_lat")
    private final double lat;

    @Column(value = "l_country")
    private final String country;

    @Column(value = "l_chat_id")
    private final Long chatId;

    public Location(String name, String localName, double lon, double lat, String country, Long chatId) {
        this(null, name, localName, lon, lat, country,chatId);
    }

    @PersistenceCreator
    public Location(UUID id, String name, String localName, double lon, double lat, String country, Long chatId) {
        this.id = id;
        this.name = name;
        this.localName = localName;
        this.lon = lon;
        this.lat = lat;
        this.country = country;
        this.chatId = chatId;
    }

    public Location(LocationDTO locationDTO, long chatId) {
        this(null, locationDTO.getName(), locationDTO.getLocalName().getRuName(), locationDTO.getLon(), locationDTO.getLat(), locationDTO.getCountry(), chatId);
    }

    public Location(UUID id, LocationDTO locationDTO, long chatId) {
        this(id,locationDTO.getName(), locationDTO.getLocalName().getRuName(), locationDTO.getLon(), locationDTO.getLat(), locationDTO.getCountry(), chatId);
    }
}
