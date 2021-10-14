package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.ArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArtistRepository extends MongoRepository<ArtistEntity, String> {
    Optional<ArtistEntity> findByFirstNameAndFamilyName(String firstName, String familyName);
}
