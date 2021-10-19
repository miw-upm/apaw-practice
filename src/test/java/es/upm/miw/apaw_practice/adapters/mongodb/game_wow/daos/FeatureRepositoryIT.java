package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class FeatureRepositoryIT {

    @Autowired
    private FeatureRepository featureRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.featureRepository.findAll().stream()
                .anyMatch(feature ->
                        "Trinket" .equals(feature.getPart()) &&
                                feature.getId() != null &&
                                158 == feature.getSpellPower() &&
                                feature.getMeleeAtack() == null &&
                                feature.getTemple() == 0 &&
                                "Use: Restores 1625 mana" .equals(feature.getExtraSpell())
                ));
    }
}
