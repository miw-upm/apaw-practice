package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FeatureService {

    private final FeaturePersistence featurePersistence;
    private final RaidPersistence raidPersistence;

    @Autowired
    public FeatureService(FeaturePersistence featurePersistence,RaidPersistence raidPersistence) {
        this.featurePersistence = featurePersistence;
        this.raidPersistence = raidPersistence;
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

    public List<String> findByDescriptionBoss(String descriptionBoss) {
        return raidPersistence.findByDescriptionBoss(descriptionBoss);
    }
}
