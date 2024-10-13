package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.basketball.BasketPlayerResource.BASKETPLAYERS;
import static es.upm.miw.apaw_practice.adapters.rest.basketball.BasketPlayerResource.SEARCH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class BasketPlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindById(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BASKETPLAYERS + SEARCH)
                                .queryParam("dni","12345678A")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BasketPlayer.class)
                .value(Assertions::assertNotNull)
                .value(basketPlayer -> {
                    assertEquals("Lebron", basketPlayer.getName());
                    assertEquals(7, basketPlayer.getDorsal());
                    assertEquals(30, basketPlayer.getPoints());
                });
    }

    @Test
    void testFindByIdNotFound(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BASKETPLAYERS + SEARCH)
                                .queryParam("dni","00000000A")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}