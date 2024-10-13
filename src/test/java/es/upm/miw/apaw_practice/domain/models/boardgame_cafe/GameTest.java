package es.upm.miw.apaw_practice.domain.models.boardgame_cafe;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GameTest {

    @Test
    void testGameBuilder() {
        Game instance = Game.buider()
                .gameName("Wingspan")
                .numPlayers(5)
                .genre("Family")
                .numberOfCopies(3)
                .build();
        assertEquals("Wingspan", instance.getGameName());
        assertEquals(5, instance.getNumPlayers());
        assertEquals("Family", instance.getGenre());
        assertEquals(3, instance.getNumberOfCopies());
    }
}
