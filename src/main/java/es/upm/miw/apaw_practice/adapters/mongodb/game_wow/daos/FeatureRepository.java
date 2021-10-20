package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeatureRepository extends MongoRepository <FeatureEntity, String> {
    Optional<FeatureEntity> findByPart(String part);
}
