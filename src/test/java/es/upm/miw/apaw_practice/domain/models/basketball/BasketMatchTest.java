package es.upm.miw.apaw_practice.domain.models.basketball;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BasketMatchTest {

    @Test
    void testBuilder() {
        BasketMatch match = BasketMatch.builder()
                .id(1)
                .date(LocalDateTime.of(2024, 10, 20, 15, 30))
                .address("123 Main St")
                .basketPlayers(List.of())
                .build();

        assertNotNull(match);
        assertEquals(1, match.getId());
        assertEquals(LocalDateTime.of(2024, 10, 20, 15, 30), match.getDate());
        assertEquals("123 Main St", match.getAddress());
        assertNotNull(match.getPlayers());
        assertTrue(match.getPlayers().isEmpty());
    }

    @Test
    void testBuilderWithOptionals() {
        BasketPlayer player1 = new BasketPlayer("12345678A", "Michael Jordan", 24, 25);
        BasketPlayer player2 = new BasketPlayer("87654321M", "Steph Curry", 30, 40);

        BasketMatch match = BasketMatch.builder()
                .id(2)
                .date(LocalDateTime.of(2024, 11, 5, 18, 45))
                .address("456 Broadway Ave")
                .basketPlayers(List.of(player1, player2))
                .build();

        assertNotNull(match);
        assertEquals(2, match.getId());
        assertEquals(LocalDateTime.of(2024, 11, 5, 18, 45), match.getDate());
        assertEquals("456 Broadway Ave", match.getAddress());
        assertNotNull(match.getPlayers());
        assertEquals(2, match.getPlayers().size()); // Comprobamos que la lista tiene 2 jugadores
        assertEquals("Michael Jordan", match.getPlayers().get(0).getName());
        assertEquals("Steph Curry", match.getPlayers().get(1).getName());
    }
}
