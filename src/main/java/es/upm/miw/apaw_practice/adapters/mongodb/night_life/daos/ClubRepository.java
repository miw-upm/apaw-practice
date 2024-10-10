package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ClubEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ClubRepository extends MongoRepository<ClubEntity, String> {
    Optional<ClubEntity> findByName(String name);
}
