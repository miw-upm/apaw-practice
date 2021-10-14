package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.DropEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestConfig
public class BossRepositoryIT {

    @Autowired
    private BossRepository bossRepository;
    private BossEntity bossEntity;

    @BeforeEach
    void before() {
        FeatureEntity[] features = {
                new FeatureEntity(new Feature("Trinket",158,null,null,"Use: Restores 1625 mana")),
                new FeatureEntity(new Feature("Neck",null,79,null,null))};
        DropEntity[] drops = {
                new DropEntity("Sliver of Pure Ice","mage,paladin,druid,priest,shaman,warlock",251,features[0]),
                new DropEntity("Marrowgar's Scratching Choker","paladin,warrior,dk",251,features[1])};
        this.bossEntity = new BossEntity("Lord Marrowgal", "10N", List.of(drops[0],drops[1]));
    }

    @Test
    void findByEffort(){
        List<BossEntity> bossesByEffort = this.bossRepository.findByEffort("10N");
        assertEquals(true, bossesByEffort.contains(bossEntity));
        assertEquals("Lord Marrowgal", bossesByEffort.get(0).getDescription());
        assertEquals("10N", bossesByEffort.get(0).getEffort());
        assertEquals("Sliver of Pure Ice", bossesByEffort.get(0).getDropList().get(0).getTitle());
        assertEquals("mage,paladin,druid,priest,shaman,warlock", bossesByEffort.get(0).getDropList().get(0).getRace());
        assertEquals(251, bossesByEffort.get(0).getDropList().get(0).getLevel());
        assertEquals("Neck", bossesByEffort.get(0).getDropList().get(1).getFeature().getPart());
        assertNull (bossesByEffort.get(0).getDropList().get(1).getFeature().getSpellPower(),"spellPower = null");
        assertEquals(79, bossesByEffort.get(0).getDropList().get(1).getFeature().getMeleeAtack());
        assertNull (bossesByEffort.get(0).getDropList().get(1).getFeature().getTemple(),"temple = null");
        assertNull (bossesByEffort.get(0).getDropList().get(1).getFeature().getExtraSpell(),"extraSpell = null");
    }

}
