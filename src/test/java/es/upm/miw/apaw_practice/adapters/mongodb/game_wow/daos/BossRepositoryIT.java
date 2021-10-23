package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class BossRepositoryIT {

    @Autowired
    private BossRepository bossRepository;

    @Test
    void findByEffort() {
        List<BossEntity> bossesByEffort = this.bossRepository.findByEffort("10N");
        assertEquals("Lord Marrowgal", bossesByEffort.get(0).getDescription());
        assertEquals("10N", bossesByEffort.get(0).getEffort());
        assertEquals("Sliver of Pure Ice", bossesByEffort.get(0).getDropList().get(0).getTitle());
        assertEquals("mage,paladin,druid,priest,shaman,warlock", bossesByEffort.get(0).getDropList().get(0).getRace());
        assertEquals(251, bossesByEffort.get(0).getDropList().get(0).getLevel());
        assertEquals("Neck", bossesByEffort.get(0).getDropList().get(1).getFeature().getPart());
        assertNull(bossesByEffort.get(0).getDropList().get(1).getFeature().getSpellPower(), "spellPower = null");
        assertEquals(79, bossesByEffort.get(0).getDropList().get(1).getFeature().getMeleeAtack());
        assertEquals(0, bossesByEffort.get(0).getDropList().get(1).getFeature().getTemple());
        assertNull(bossesByEffort.get(0).getDropList().get(1).getFeature().getExtraSpell(), "extraSpell = null");
    }

    @Test
    void testReadAll() {
        assertTrue(this.bossRepository.findAll().stream()
                .anyMatch(boss ->
                        "GunShip Battle" .equals(boss.getDescription()) &&
                                "10N" .equalsIgnoreCase(boss.getEffort()) &&
                                "Cord of Dark Suffering" .equalsIgnoreCase(boss.getDropList().get(0).getTitle()) &&
                                "druid,rogue" .equalsIgnoreCase(boss.getDropList().get(0).getRace()) &&
                                251 == boss.getDropList().get(0).getLevel() &&
                                "Waist" .equalsIgnoreCase(boss.getDropList().get(0).getFeature().getPart()) &&
                                106 == boss.getDropList().get(0).getFeature().getSpellPower() &&
                                null == boss.getDropList().get(0).getFeature().getMeleeAtack() &&
                                0 == boss.getDropList().get(0).getFeature().getTemple() &&
                                null == boss.getDropList().get(0).getFeature().getExtraSpell()
                ));
    }
}
