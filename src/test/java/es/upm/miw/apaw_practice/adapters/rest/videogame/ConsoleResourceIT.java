package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.videogame.Console;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}