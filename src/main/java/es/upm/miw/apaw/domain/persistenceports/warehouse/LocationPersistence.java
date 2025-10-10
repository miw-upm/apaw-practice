package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface LocationPersistence {

    Stream<Location> readAll();
    Location update(UUID id, Location location);

}
