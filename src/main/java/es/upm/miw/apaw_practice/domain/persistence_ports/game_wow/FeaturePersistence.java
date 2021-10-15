package es.upm.miw.apaw_practice.domain.persistence_ports.game_wow;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturePersistence {
    Feature create(Feature feature);
    Feature read(String part);
}
