package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RestTestConfig
public class PlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PlayerPersistence playerPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;

    @Test
    void testDeletePlayer(){
        assertNotNull(playerPersistence.readyByPlayerName("Julia"));
        this.webTestClient
                .delete()
                .uri(PlayerResource.PLAYERS + PlayerResource.PLAYER_NAME, "Julia")
                .exchange()
                .expectStatus()
                .isOk();
        assertThrows(NotFoundException.class, () -> playerPersistence.readyByPlayerName("Julia"));
        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }
}
