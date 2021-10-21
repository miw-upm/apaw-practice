package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Test
    void testFindBySpellPower(){
        List<FeatureEntity> featuresBySpellPower = this.featureRepository.findBySpellPower(106);
        assertEquals("Feet", featuresBySpellPower.get(0).getPart());
        assertEquals(106, featuresBySpellPower.get(0).getSpellPower());
        assertNull( featuresBySpellPower.get(0).getMeleeAtack());
        assertEquals(0, featuresBySpellPower.get(0).getTemple());
        assertEquals( "Use: Restores 1625 mana",featuresBySpellPower.get(0).getExtraSpell());
    }
}
