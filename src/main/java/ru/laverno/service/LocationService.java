package ru.laverno.service;

import ru.laverno.entity.Location;

public interface LocationService {

    Location getLocationByChatId(long chatId);

    Location setLocation(long chatId, String city);
}
