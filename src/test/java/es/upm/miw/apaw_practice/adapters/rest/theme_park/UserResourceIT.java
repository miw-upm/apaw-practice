package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;

import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.UserPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

@RestTestConfig
class UserResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserPersistence userPersistence;

    @Test
    void testCreate() {
        User user =
                new User("9384","C/Melody, 32", LocalDateTime.of(2006,9,21,21,55), false);
        this.webTestClient
                .post()
                .uri(UserResource.USERS)
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        User user =
                new User("41", "C/ repeat, 00", LocalDateTime.of(2010,5,4,1,35), true);
        this.webTestClient
                .post()
                .uri(UserResource.USERS)
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        User user = new User("ABCDEF", "C/test", LocalDateTime.of(2005,12,12,3,3,4), true);

        this.webTestClient
                .put()
                .uri(UserResource.USERS + UserResource.ID_MEMBERSHIP, "kk")
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus().isNotFound();
    }
}
