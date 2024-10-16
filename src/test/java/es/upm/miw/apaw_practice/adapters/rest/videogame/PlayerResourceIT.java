package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class PlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PlayerPersistence playerPersistence;

    @Autowired
    private VideoGameSeederService videoGameSeederService;
    @Autowired
    private PlayerResource playerResource;

    @Test
    void testDeletePlayer(){
        assertNotNull(playerPersistence.readyByPlayerName("Julia"));
        this.webTestClient
                .delete()
                .uri(playerResource.PLAYERS + playerResource.PLAYER_NAME, "Julia")
                .exchange()
                .expectStatus()
                .isOk();
        assertThrows(NotFoundException.class, () -> playerPersistence.readyByPlayerName("Julia"));
        videoGameSeederService.deleteAll();
        videoGameSeederService.seedDatabase();
    }

    @Test
    void testFindVideoGameAliasByPlayerName(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(playerResource.PLAYERS + playerResource.SEARCH + playerResource.VIDEOGAMEALIAS_BY_PLAYERNAMES)
                                .queryParam("l","playerName: Julia")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(name -> assertEquals(List.of("Call of Duty"), name));
    }
}
