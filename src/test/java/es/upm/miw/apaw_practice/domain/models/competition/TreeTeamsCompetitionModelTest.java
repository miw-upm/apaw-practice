package es.upm.miw.apaw_practice.domain.models.competition;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TreeTeamsCompetitionModelTest {

    private TeamCompetition nationalTeam;
    private TeamCompetition footballTeam;
    private TeamCompetition footballElevenTeam;
    private TeamCompetition footballInDoorTeam;
    private TeamCompetition waterpoloTeam;
    private PlayerTeam player1;
    private PlayerTeam player2;
    private PlayerTeam player3;
    private PlayerTeam player4;
    private PlayerTeam player5;

    @BeforeEach
    void ini() {
        this.player1 = new PlayerTeam("1", 70.0, 1.80, new BigDecimal("50000"));
        this.player2 = new PlayerTeam("2", 80.0, 1.85, new BigDecimal("40000"));
        this.player3 = new PlayerTeam("3", 60.0, 1.90, new BigDecimal("30000"));
        this.player4 = new PlayerTeam("4", 55.0, 1.87, new BigDecimal("25000"));
        this.player5 = new PlayerTeam("5", 90.0, 1.57, new BigDecimal("20000"));


        this.footballElevenTeam = new TeamCompetition("Seleccion Española de Fútbol 11", 2, "Luis Enrique");
        footballElevenTeam.getPlayerTeams().add(player1);
        footballElevenTeam.getPlayerTeams().add(player2);
        this.footballInDoorTeam = new TeamCompetition("Seleccion Española de Fútbol Sala", 10, "Kike Boned");
        footballInDoorTeam.getPlayerTeams().add(player3);
        footballInDoorTeam.getPlayerTeams().add(player4);
        footballInDoorTeam.getPlayerTeams().add(player5);

        ArrayList<TeamCompetition> competitions = new ArrayList<>();
        competitions.add(footballElevenTeam);
        competitions.add(footballInDoorTeam);

        this.footballTeam = new TeamCompetition("Fútbol", 5, "Entrenador Fútbol", competitions);

        this.waterpoloTeam = new TeamCompetition("Waterpolo", 3, "Entrenador Waterpolo");

        ArrayList<TeamCompetition> competitions2 = new ArrayList<>();
        competitions2.add(footballTeam);
        competitions2.add(waterpoloTeam);

        this.nationalTeam = new TeamCompetition("Selección Nacional", 15, "Directivo", competitions2);
    }

    @Test
    void testAddLeafToComposite() {
        TeamCompetition teamCompetition = new TeamCompetition("Leaf Team", 0, "Leaf Coach", List.of());
        nationalTeam.add(teamCompetition);
        assertTrue(nationalTeam.getChildren().contains(teamCompetition));
    }

    @Test
    void testLeafDoesNotAddFromComposite() {
        assertThrows(UnsupportedOperationException.class, () -> {
            footballElevenTeam.add(new TeamCompetition("Leaf Team", 0, "Leaf Coach", List.of()));
        });
    }

    @Test
    void testCompositeCanRemoveLeaf() {
        footballTeam.remove(footballElevenTeam);
        assertFalse(footballTeam.getChildren().contains(footballElevenTeam));
    }

    @Test
    void testLeafDoesNotRemoveFromComposite() {
        assertThrows(UnsupportedOperationException.class, () -> {
            footballElevenTeam.remove(footballInDoorTeam);
        });
    }

    @Test
    void testIsComposite() {
        assertTrue(nationalTeam.isComposite());
        assertTrue(footballTeam.isComposite());
        assertFalse(footballElevenTeam.isComposite());
    }

    @Test
    void testGetChildrenComposite() {
        assertEquals(2, this.footballTeam.getChildren().size());
        assertTrue(footballTeam.getChildren().contains(footballElevenTeam));
        assertTrue(footballTeam.getChildren().contains(footballInDoorTeam));
    }

    @Test
    void testGetChildrenLeaf() {
        assertThrows(UnsupportedOperationException.class, () -> {
            footballElevenTeam.getChildren();
        });
    }

    @Test
    void testCompositeCanHaveMultipleLeaves() {
        TeamCompetition teamCompetition = new TeamCompetition("New Leaf Team", 1, "New Coach", List.of());
        footballTeam.add(teamCompetition);
        assertTrue(footballTeam.getChildren().contains(teamCompetition));
        assertEquals(3, footballTeam.getChildren().size());
    }

    @Test
    void testGetAllPlayersFromComposite() {
        List<PlayerTeam> allPlayers = nationalTeam.getAllPlayers();

        assertEquals(5, allPlayers.size());

        assertTrue(allPlayers.contains(player1));
        assertTrue(allPlayers.contains(player2));
        assertTrue(allPlayers.contains(player3));
        assertTrue(allPlayers.contains(player4));
        assertTrue(allPlayers.contains(player5));
    }

    @Test
    void testGetAllPlayersFromLeaf() {
        List<PlayerTeam> allPlayers = footballElevenTeam.getPlayerTeams();

        assertEquals(2, allPlayers.size());

        assertTrue(allPlayers.contains(player1));
        assertTrue(allPlayers.contains(player2));
    }
}
