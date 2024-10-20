package es.upm.miw.apaw_practice.domain.models.basketball;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class BasketPlayerTest {

    @Test
    void testBuilder(){
        BasketPlayer basketPlayer = BasketPlayer.builder()
                .dni("87654321B")
                .name("Steph Curry")
                .dorsal(30)
                .points(50)
                .build();
        assertNotNull(basketPlayer);
        assertEquals("87654321B", basketPlayer.getDni());
        assertEquals("Steph Curry", basketPlayer.getName());
        assertEquals(30, basketPlayer.getDorsal());
        assertEquals(50, basketPlayer.getPoints());
    }
}
