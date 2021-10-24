package es.upm.miw.apaw_practice.domain.models.cinema;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class SpectatorTest {

    @Test
    void testBuilder() {
        Spectator spectator = Spectator.builder()
                .idCard("45643j")
                .name("Sara")
                .familyName("Garcia")
                .build();
        assertEquals("45643j", spectator.getIdCard());
        assertEquals("Sara", spectator.getName());
        assertEquals("Garcia", spectator.getFamilyName());
    }
}
