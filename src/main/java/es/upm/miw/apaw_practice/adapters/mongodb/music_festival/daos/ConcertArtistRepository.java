package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import java.util.Optional;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConcertArtistRepository extends MongoRepository<ConcertArtistEntity, String> {
    Optional<ConcertArtistEntity> findByName(String name);
}
