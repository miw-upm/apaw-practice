package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.ConcertEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConcertRepository extends MongoRepository<ConcertEntity, String> {
}
