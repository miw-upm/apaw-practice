package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.SongEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<SongEntity, String> {
}
