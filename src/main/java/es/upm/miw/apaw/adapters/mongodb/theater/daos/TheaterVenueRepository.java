package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterVenueEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TheaterVenueRepository extends MongoRepository<TheaterVenueEntity, UUID> {
    Optional<TheaterVenueEntity> findByVenueCode(String venueCode);

    int deleteByVenueCode(String venueCode);
}
