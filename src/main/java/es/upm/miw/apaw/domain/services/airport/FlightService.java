package es.upm.miw.apaw.domain.services.airport;

import es.upm.miw.apaw.domain.models.airport.Flight;
import es.upm.miw.apaw.domain.persistenceports.airport.FlightPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightService {

    private final FlightPersistence flightPersistence;

    @Autowired
    public FlightService(FlightPersistence flightPersistence) {
        this.flightPersistence = flightPersistence;
    }

    public Flight read(UUID id) {
        return flightPersistence.read(id);
    }
}
