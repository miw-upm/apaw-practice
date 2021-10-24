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


@RestTestConfig
public class AthleteResourceIT {

    @Autowired
    private WebTestClient webTestClient;



    @Test
    void testCreate() {
        Athlete athlete =
                Athlete.builder().nie("123456a").name("luca").familyName("Modric").build();

        this.webTestClient
                .post()
                .uri(AthleteResource.ATHLETE)
                .body(BodyInserters.fromValue(athlete))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Athlete.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Athlete athlete =
                Athlete.builder().nie("88888888a").name("sergio").familyName("ramos").build();

        this.webTestClient
                .post()
                .uri(AthleteResource.ATHLETE)
                .body(BodyInserters.fromValue(athlete))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateName() {
        AthleteNameUpdating athleteNameUpdating = new AthleteNameUpdating("ana", "karim");
        this.webTestClient
                .patch()
                .uri(AthleteResource.ATHLETE + AthleteResource.NAME)
                .body(BodyInserters.fromValue(athleteNameUpdating))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateNameConflict() {
        AthleteNameUpdating athleteNameUpdating = new AthleteNameUpdating();
        this.webTestClient
                .patch()
                .uri(AthleteResource.ATHLETE + AthleteResource.NAME)
                .body(BodyInserters.fromValue(athleteNameUpdating))
                .exchange()
                .expectStatus().isBadRequest();
    }


}
