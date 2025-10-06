package es.upm.miw.apaw.adapters.mongodb.airport.persistence;

import es.upm.miw.apaw.adapters.mongodb.airport.daos.FlightRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.airport.Flight;
import es.upm.miw.apaw.domain.persistenceports.airport.FlightPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("airportPersistence")
public class FlightPersistenceMongodb implements FlightPersistence {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightPersistenceMongodb(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight read(UUID id) {
        return this.flightRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" Flight id: " + id))
                .toFlight();
    }
}
