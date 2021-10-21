package es.upm.miw.apaw_practice.domain.services.football;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class FootballPlayerServiceIT {

    @Autowired
    private FootballPlayerService footballPlayerService;

    @Test
    void testFindFootballPlayersByPrincipalRefereeName() {

        FootballPlayer expectedFootballPlayer = new FootballPlayer(Boolean.TRUE, 10, 24, "Cristiano");
        List<FootballPlayer> result = this.footballPlayerService.findFootballPlayersByPrincipalRefereeName("Undiano").collect(Collectors.toList());

        assertEquals(expectedFootballPlayer.isDefense(), result.get(0).isDefense());
        assertEquals(expectedFootballPlayer.getGoalsScored(), result.get(0).getGoalsScored());
        assertEquals(expectedFootballPlayer.getAge(), result.get(0).getAge());
        assertEquals(expectedFootballPlayer.getName(), result.get(0).getName());

        List<FootballPlayer> expectedFootballPlayers2 = new ArrayList<>();
        expectedFootballPlayers2.add(new FootballPlayer(Boolean.TRUE, 10, 24, "Cristiano"));
        expectedFootballPlayers2.add(new FootballPlayer(Boolean.TRUE, 2, 24, "Yuri"));
        List<FootballPlayer> result2 = this.footballPlayerService.findFootballPlayersByPrincipalRefereeName("Jose").collect(Collectors.toList());

        assertEquals(expectedFootballPlayers2.get(0).isDefense(), result2.get(0).isDefense());
        assertEquals(expectedFootballPlayers2.get(0).getGoalsScored(), result2.get(0).getGoalsScored());
        assertEquals(expectedFootballPlayers2.get(0).getAge(), result2.get(0).getAge());
        assertEquals(expectedFootballPlayers2.get(0).getName(), result2.get(0).getName());
        assertEquals(expectedFootballPlayers2.get(1).isDefense(), result2.get(1).isDefense());
        assertEquals(expectedFootballPlayers2.get(1).getGoalsScored(), result2.get(1).getGoalsScored());
        assertEquals(expectedFootballPlayers2.get(1).getAge(), result2.get(1).getAge());
        assertEquals(expectedFootballPlayers2.get(1).getName(), result2.get(1).getName());
    }
}
