package es.upm.miw.apaw_practice.domain.game_wow;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import es.upm.miw.apaw_practice.domain.services.game_wow.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class FeatureSeviceIT {

    @Autowired
    private FeatureService featureService;
    @Autowired
    private FeaturePersistence featurePersistence;

    @Test
    void testUpdateTemple (){
        this.featureService.updateTemple(150);
        assertEquals(150, this.featurePersistence.read("Trinket").getTemple());
        assertEquals(150, this.featurePersistence.read("Neck").getTemple());
        assertEquals(150, this.featurePersistence.read("Feet").getTemple());
        assertEquals(150, this.featurePersistence.read("Chest").getTemple());
        assertEquals(150, this.featurePersistence.read("Waist").getTemple());
        assertEquals(150, this.featurePersistence.read("Legs").getTemple());
    }
}
