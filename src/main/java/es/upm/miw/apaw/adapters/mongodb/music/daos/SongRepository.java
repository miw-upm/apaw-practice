package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<SongEntity, String> {
}
