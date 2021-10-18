package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FeatureService {

    private final FeaturePersistence featurePersistence;

    @Autowired
    public FeatureService(FeaturePersistence featurePersistence) {
        this.featurePersistence = featurePersistence;
    }

    public Feature create(Feature feature) {
        return featurePersistence.create(feature);
    }

    public void updateTemple(Integer temple) {
        Stream<Feature> features = featurePersistence.readAll();
        features.forEach(feature -> {
            feature.setTemple(temple);
            this.featurePersistence.update(feature);
        });
    }
}
