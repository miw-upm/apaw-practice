package es.upm.miw.apaw_practice.domain.models.football;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class MatchTest {

    @Test
    void testBuilder() {

        FootballPlayer[] playersTest = {
                FootballPlayer.builder().defense(Boolean.TRUE).goalsScored(10).age(24).name("FootballPlayerTest1").build(),
                FootballPlayer.builder().defense(Boolean.FALSE).goalsScored(20).age(22).name("FootballPlayerTest2").build(),
                FootballPlayer.builder().defense(Boolean.FALSE).goalsScored(0).age(19).name("FootballPlayerTest3").build(),
                FootballPlayer.builder().defense(Boolean.TRUE).goalsScored(2).age(24).name("FootballPlayerTest4").build(),
                FootballPlayer.builder().defense(Boolean.FALSE).goalsScored(7).age(35).name("FootballPlayerTest5").build(),
                FootballPlayer.builder().defense(Boolean.FALSE).goalsScored(8).age(40).name("FootballPlayerTest6").build()
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
