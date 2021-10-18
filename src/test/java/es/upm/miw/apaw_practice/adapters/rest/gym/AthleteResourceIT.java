package es.upm.miw.apaw_practice.adapters.rest.gym;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import es.upm.miw.apaw_practice.domain.models.gym.AthleteNameUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class AthleteResourceIT {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testFindeByNie() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(AthleteResource.athlets + AthleteResource.ID)
                                .queryParam("nie", "55555555a")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Athlete.class)
                .value(athletes -> assertEquals("ana", athletes.get(0).getName()));
    }

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

    @Test
    void testUpdateName() {
        AthleteNameUpdating athleteNameUpdating = new AthleteNameUpdating("ana", "karim");
        this.webTestClient
                .patch()
                .uri(AthleteResource.athlets + AthleteResource.Name)
                .body(BodyInserters.fromValue(athleteNameUpdating))
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(AthleteResource.athlets + AthleteResource.ID)
                                .queryParam("nie", "55555555a")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Athlete.class)
                .value(athletes -> assertEquals("karim", athletes.get(0).getName()));
    }

    @Test
    void testUpdateNameConflict() {
        AthleteNameUpdating athleteNameUpdating = new AthleteNameUpdating();
        this.webTestClient
                .patch()
                .uri(AthleteResource.athlets + AthleteResource.Name)
                .body(BodyInserters.fromValue(athleteNameUpdating))
                .exchange()
                .expectStatus().isBadRequest();
    }


}
