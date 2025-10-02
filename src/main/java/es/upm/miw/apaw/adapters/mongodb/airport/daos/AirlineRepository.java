package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.AirlineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AirlineRepository extends MongoRepository<AirlineEntity, UUID> {
}
