package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static es.upm.miw.apaw_practice.adapters.rest.videogame.ConsoleResource.*;

@RestTestConfig
 class ConsoleResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByConsoleReference(){
        this.webTestClient.
                get()
                .uri(uriBuilder -> uriBuilder.path(CONSOLES + SEARCH)
                        .queryParam("console","consoleReference:Xbox")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Console.class)
                .value(consoles -> assertFalse(consoles.isEmpty()))
                .value(consoles -> assertEquals("Xbox", consoles.get(0).getConsoleReference()))
                .value(consoles -> assertTrue(consoles.get(0).getConsoleReference().contains("Xbox")));
    }

    @Test
    void testFindByConsoleReferenceThanBadRequest(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CONSOLES + SEARCH)
                                .queryParam("console","NOSTRADAMUS")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testDelete(){
        this.webTestClient
                .delete()
                .uri(CONSOLES + CONSOLE_REFERENCE, "GameCube")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreate(){
        VideoGame[] videoGames = {
                new VideoGame("Stable",2,true, LocalDate.of(2012,9,16))
        };

        Console console =
                new Console("Rabbit", 9875456464646L, true,LocalDate.of(1985,9,6), List.of(videoGames[0]));

        this.webTestClient
                .post()
                .uri(CONSOLES)
                .body(BodyInserters.fromValue(console))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Console.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testUpdate(){
        Console console =
                new Console("Rabbit", 9875456464646L, true,LocalDate.of(1985,9,6), Collections.emptyList());
        this.webTestClient
                .put()
                .uri(CONSOLES + CONSOLE_REFERENCE, "Rabbit 2")
                .body(BodyInserters.fromValue(console))
                .exchange()
                .expectStatus().isNotFound();
    }
}