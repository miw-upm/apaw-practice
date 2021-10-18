package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestConfig
public class BossRepositoryIT {

    @Autowired
    private BossRepository bossRepository;

    @Test
    void findByEffort(){
        List<BossEntity> bossesByEffort = this.bossRepository.findByEffort("10N");
        assertEquals("Lord Marrowgal", bossesByEffort.get(0).getDescription());
        assertEquals("10N", bossesByEffort.get(0).getEffort());
        assertEquals("Sliver of Pure Ice", bossesByEffort.get(0).getDropList().get(0).getTitle());
        assertEquals("mage,paladin,druid,priest,shaman,warlock", bossesByEffort.get(0).getDropList().get(0).getRace());
        assertEquals(251, bossesByEffort.get(0).getDropList().get(0).getLevel());
        assertEquals("Neck", bossesByEffort.get(0).getDropList().get(1).getFeature().getPart());
        assertNull (bossesByEffort.get(0).getDropList().get(1).getFeature().getSpellPower(),"spellPower = null");
        assertEquals(79, bossesByEffort.get(0).getDropList().get(1).getFeature().getMeleeAtack());
        assertEquals(0,bossesByEffort.get(0).getDropList().get(1).getFeature().getTemple());
        assertNull (bossesByEffort.get(0).getDropList().get(1).getFeature().getExtraSpell(),"extraSpell = null");
    }

}
