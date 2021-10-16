package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FeatureRepository extends MongoRepository <FeatureEntity, String> {
    Optional<FeatureEntity> findByPart(String part);
}
