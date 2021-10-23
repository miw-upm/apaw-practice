package es.upm.miw.apaw_practice.domain.models.game_wow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeatureTest {

    @Test
    void testBuilderFull() {
        Feature feature = Feature.builder()
                .part("Head")
                .spellPower(200)
                .meleeAtack(150)
                .temple(80)
                .extraSpell("Use: Restores 2000 life")
                .build();
        assertEquals("Head", feature.getPart());
        assertEquals(200, feature.getSpellPower());
        assertEquals(150, feature.getMeleeAtack());
        assertEquals(80, feature.getTemple());
        assertEquals("Use: Restores 2000 life", feature.getExtraSpell());
    }

    @Test
    void testBuildererPartial() {
        Feature feature = Feature.builder()
                .part("Head")
                .spellPower(200)
                .build();
        assertEquals("Head", feature.getPart());
        assertEquals(200, feature.getSpellPower());
    }
}
