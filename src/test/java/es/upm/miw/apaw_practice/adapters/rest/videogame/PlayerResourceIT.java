package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.VideoGameSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.videogame.PlayerResource.*;
import static org.junit.jupiter.api.Assertions.*;

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
                .uri(PLAYERS + PLAYER_NAME, "Julia")
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
                        uriBuilder.path(PLAYERS + SEARCH + VIDEOGAMEALIAS_BY_PLAYERNAMES)
                                .queryParam("l","playerName: Julia")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(name -> assertEquals(List.of("Call of Duty"), name));
    }

    @Test
    void testUpdate(){
        Console console =
                new Console("Rabbit", 9875456464646L, true,LocalDate.of(1985,9,6), Collections.emptyList());
        Player player = new Player("Andres",23,false, LocalDate.of(1961,5,12), console);
        this.webTestClient
                .put()
                .uri(PLAYERS + PLAYER_NAME, "Antonio")
                .body(BodyInserters.fromValue(player))
                .exchange()
                .expectStatus().isNotFound();
    }
}