package es.upm.miw.apaw_practice.domain.models.competition;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

@TestConfig
class TreeTeamsCompetitionTest {

    private TreeTeamsComposite nationalTeam;
    private TreeTeamsComposite footballTeam;
    private TreeTeamsCompetitionLeaf footballElevenTeam;
    private TreeTeamsCompetitionLeaf footballInDoorTeam;
    private TreeTeamsComposite waterpoloTeam;
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

        this.nationalTeam = new TreeTeamsComposite();

        this.footballTeam = new TreeTeamsComposite();

        this.waterpoloTeam = new TreeTeamsComposite();

        this.footballElevenTeam = new TreeTeamsCompetitionLeaf(new TeamCompetition("Seleccion Española de Fútbol 11", 2, "Luis Enrique"));
        footballElevenTeam.getAllPlayers().add(player1);
        footballElevenTeam.getAllPlayers().add(player2);
        this.footballInDoorTeam = new TreeTeamsCompetitionLeaf(new TeamCompetition("Seleccion Española de Fútbol Sala", 10, "Kike Boned"));
        footballInDoorTeam.getAllPlayers().add(player3);
        footballInDoorTeam.getAllPlayers().add(player4);
        footballInDoorTeam.getAllPlayers().add(player5);

        this.footballTeam.add(this.footballElevenTeam);
        this.footballTeam.add(this.footballInDoorTeam);

        this.nationalTeam.add(this.footballTeam);
        this.nationalTeam.add(this.waterpoloTeam);
    }

    @Test
    void testAddLeafToComposite() {
        TreeTeamsCompetitionLeaf leafTeam = new TreeTeamsCompetitionLeaf(new TeamCompetition("Leaf Team", 0, "Leaf Coach", List.of()));
        nationalTeam.add(leafTeam);
        assertTrue(nationalTeam.getChildren().contains(leafTeam));
    }

    @Test
    void testLeafDoesNotAddFromComposite() {
        assertThrows(UnsupportedOperationException.class, () -> {
            footballElevenTeam.add(new TreeTeamsCompetitionLeaf(new TeamCompetition("Leaf Team", 0, "Leaf Coach", List.of())));
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
        assertEquals(2, footballTeam.getChildren().size());
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
        TreeTeamsCompetitionLeaf newLeaf = new TreeTeamsCompetitionLeaf(new TeamCompetition("New Leaf Team", 1, "New Coach", List.of()));
        footballTeam.add(newLeaf);
        assertTrue(footballTeam.getChildren().contains(newLeaf));
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
        List<PlayerTeam> allPlayers = footballElevenTeam.getAllPlayers();

        assertEquals(2, allPlayers.size());

        assertTrue(allPlayers.contains(player1));
        assertTrue(allPlayers.contains(player2));
    }
}
