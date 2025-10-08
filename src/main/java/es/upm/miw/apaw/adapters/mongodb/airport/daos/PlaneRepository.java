package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.PlaneEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlaneRepository extends MongoRepository<PlaneEntity, UUID> {
    Optional<PlaneEntity> findByRegistrationNumber(String registrationNumber);
}
