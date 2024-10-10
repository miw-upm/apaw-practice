package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArtistRepository extends MongoRepository<ArtistEntity, String> {
    Optional<ArtistEntity> findByArtistName(String artistName);
}
