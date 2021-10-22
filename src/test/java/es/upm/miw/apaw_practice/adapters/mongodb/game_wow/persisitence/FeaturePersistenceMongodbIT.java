package es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persisitence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.persistence.FeaturePersistenceMongodb;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class FeaturePersistenceMongodbIT {

    @Autowired
    private FeaturePersistenceMongodb featurePersistenceMongodb;

    @Test
    void testCreateAndRead(){
        Feature feature = Feature.builder()
                .part("Head")
                .spellPower(158)
                .meleeAtack(120)
                .temple(80)
                .extraSpell("Use: Restores 2000 mana")
                .build();
        this.featurePersistenceMongodb.create(feature);
        Feature featureBD = this.featurePersistenceMongodb.read("Head");
        assertEquals(158, featureBD.getSpellPower());
        assertEquals(120, featureBD.getMeleeAtack());
        assertEquals(80, featureBD.getTemple());
        assertEquals("Use: Restores 2000 mana", featureBD.getExtraSpell());
    }
}
