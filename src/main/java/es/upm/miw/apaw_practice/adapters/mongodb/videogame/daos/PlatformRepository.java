package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlatformEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlatformRepository extends MongoRepository<PlatformEntity, String> {
    Optional<PlatformEntity> findByConsoleName(String consoleName);
}
