package ru.laverno.repository;

import org.springframework.data.repository.CrudRepository;
import ru.laverno.entity.Location;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository extends CrudRepository<Location, UUID> {

    Optional<Location> findLocationByChatId(long chatId);
}
