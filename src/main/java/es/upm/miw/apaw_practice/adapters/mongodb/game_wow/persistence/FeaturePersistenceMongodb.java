package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.FeatureRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("featurePersistence")
public class FeaturePersistenceMongodb implements FeaturePersistence {

    private final FeatureRepository featureRepository;

    @Autowired
    public FeaturePersistenceMongodb(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature create(Feature feature) {
        return featureRepository
                .save(new FeatureEntity(feature))
                .toFeature();
    }

    @Override
    public Feature read(String part) {
        return this.featureRepository
                .findByPart(part)
                .orElseThrow(() -> new NotFoundException("Feature part: " + part))
                .toFeature();
    }
}
