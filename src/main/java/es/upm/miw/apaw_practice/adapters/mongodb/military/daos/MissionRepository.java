package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.MissionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MissionRepository extends MongoRepository<MissionEntity, String> {
    Optional<MissionEntity> findByCodeName(String codeName);
}
