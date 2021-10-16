package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class AthleteResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Athlete athlete =
                new Athlete("25436587a", "karim", "basly");
        this.webTestClient
                .post()
                .uri(AthleteResource.athlets)
                .body(BodyInserters.fromValue(athlete))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Athlete.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Athlete athlete =
                new Athlete("88888888a", "ABCD", "abds");
        this.webTestClient
                .post()
                .uri(AthleteResource.athlets)
                .body(BodyInserters.fromValue(athlete))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
