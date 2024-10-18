package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class BasketMatchResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(BasketMatchResource.MATCHES + BasketMatchResource.ID_ID, 5)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateNotFound() {
        this.webTestClient
                .patch()
                .uri(BasketMatchResource.MATCHES + BasketMatchResource.ID_ID, 10)
                .body(BodyInserters.fromValue("Parque de Vallecas"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .patch()
                .uri(BasketMatchResource.MATCHES + BasketMatchResource.ID_ID, 7)
                .body(BodyInserters.fromValue("Parque pacifico"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BasketMatch.class)
                .value(Assertions::assertNotNull)
                .value(basketMatch -> assertEquals("Parque pacifico",basketMatch.getAddress()));
    }
}
