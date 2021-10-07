package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class FeaturePersistenceMongodb implements FeaturePersistence {

    private FeaturePersistence featurePersistence;

    @Autowired
    public FeaturePersistenceMongodb(FeaturePersistence featurePersistence) {
        this.featurePersistence = featurePersistence;
    }
}
