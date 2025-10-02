package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.FlightEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FlightRepository extends MongoRepository<FlightEntity, UUID> {
}
