package es.upm.miw.apaw_practice.domain.models.football;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.FootballPlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.MatchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.PrincipalRefereeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class MatchTest {

    @Test
    void testBuilder() {

        FootballPlayer[] playersTest = {
                new FootballPlayer(Boolean.TRUE, 10, 24, "FootballPlayerTest1"),
                new FootballPlayer(Boolean.FALSE, 20, 22, "FootballPlayerTest2"),
                new FootballPlayer(Boolean.FALSE, 0, 19, "FootballPlayerTest3"),
                new FootballPlayer(Boolean.TRUE, 2, 24, "FootballPlayerTest4"),
                new FootballPlayer(Boolean.FALSE, 7, 35, "FootballPlayerTest5"),
                new FootballPlayer(Boolean.FALSE, 8, 40, "FootballPlayerTest6")
        };
        PrincipalReferee principalRefereeTest = new PrincipalReferee("test", "Ciudadtest", 99);
        Match match = Match.builder().date(LocalDateTime.of(2021, 10, 20, 21, 0))
                .weather("Cloudy")
                .round(7)
                .principalReferee(principalRefereeTest)
                .players(List.of(playersTest))
                .build();

        assertEquals("test", match.getPrincipalReferee().getName());
        assertEquals("FootballPlayerTest1", match.getPlayers().get(0).getName());
        assertEquals(LocalDateTime.of(2021, 10, 20, 21, 0), match.getDate());
        assertEquals("Cloudy", match.getWeather());
        assertEquals(7, match.getRound());

    }
}
