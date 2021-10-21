package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DroRepositoryIT {

    @Autowired
    private Droprepository droprepository;

    @Test
    void testReadAll() {
        assertTrue(this.droprepository.findAll().stream()
                .anyMatch(drop ->
                        "Ghoul Commander's Cuirass" .equals(drop.getTitle()) &&
                                "paladin,warrior,dk" .equalsIgnoreCase(drop.getRace()) &&
                                251 == drop.getLevel() &&
                                "Waist" .equalsIgnoreCase(drop.getFeature().getPart()) &&
                                drop.getFeature().getSpellPower() == 106 &&
                                null == drop.getFeature().getMeleeAtack() &&
                                drop.getFeature().getTemple() == 0 &&
                                null == drop.getFeature().getExtraSpell()
                ));
    }
}
