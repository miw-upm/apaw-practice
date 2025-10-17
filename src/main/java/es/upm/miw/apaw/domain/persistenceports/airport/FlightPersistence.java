package es.upm.miw.apaw.domain.persistenceports.airport;

import es.upm.miw.apaw.domain.models.airport.Flight;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlightPersistence {
    Flight read(UUID id);
}
