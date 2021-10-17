package es.upm.miw.apaw_practice.adapters.mongodb.music_manager.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_manager.entities.BandEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BandRepository extends MongoRepository<BandEntity, String> {
    Optional<BandEntity> findByBandName(String bandName);
}
