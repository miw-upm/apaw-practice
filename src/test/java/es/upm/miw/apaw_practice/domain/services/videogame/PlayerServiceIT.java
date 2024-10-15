package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class PlayerServiceIT {

    @Autowired
    private PlayerService playerService;

    @Autowired
    PlayerPersistence playerPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testDelete(){
        List<Player> players = playerPersistence.readAll().toList();
        assertNotNull(players.get(0));
        String playerName = players.get(0).getPlayerName();
        this.playerService.delete(playerName);
        assertThrows(NotFoundException.class, () -> playerPersistence.readyByPlayerName(playerName));

        this.videoGameSeederService.deleteAll();
        this.videoGameSeederService.seedDatabase();
    }
}
