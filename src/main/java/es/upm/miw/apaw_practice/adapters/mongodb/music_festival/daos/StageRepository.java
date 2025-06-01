package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StageRepository extends MongoRepository<StageEntity, String> {
}
