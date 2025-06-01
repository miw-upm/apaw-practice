package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertArtistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConcertArtistRepository extends MongoRepository<ConcertArtistEntity, String> {
}
