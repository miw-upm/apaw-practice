package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BandRepository extends MongoRepository<BandEntity, String> {
}
