package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.AirlineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AirlineRepository extends MongoRepository<AirlineEntity, UUID> {
    void deleteByName(String name);
    Optional<AirlineEntity> findByName(String name);
}
