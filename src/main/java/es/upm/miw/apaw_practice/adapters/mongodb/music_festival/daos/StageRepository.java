package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.StageEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StageRepository extends MongoRepository<StageEntity, String> {

    void deleteByName(String name);

    Optional<StageEntity> findByName(String name);

}
