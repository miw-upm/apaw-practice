package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.BasketballSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.basketball.PlayerResource.*;
import static org.junit.Assert.assertEquals;

@RestTestConfig
class PlayerResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BasketballSeederService basketballSeederService;
    @AfterEach
    void resetDataBase() {
        this.basketballSeederService.deleteAll();
        this.basketballSeederService.seedDatabase();
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(PLAYER + EMAIL_ID, "email3@gmail.com")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void  testReadByBasketId(){
        this.webTestClient
                .get()
                .uri(PLAYER + BASKET_ID, "canasta1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(Assertions::assertNotNull)
                .value(position -> {
                    assertEquals("alero", position);
                });
    }

}
