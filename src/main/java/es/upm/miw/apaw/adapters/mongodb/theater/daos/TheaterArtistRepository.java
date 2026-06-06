package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface TheaterArtistRepository extends MongoRepository<TheaterArtistEntity, UUID> {
    Optional<TheaterArtistEntity> findByArtistCode(String artistCode);

    int deleteByArtistCode(String artistCode);
}
