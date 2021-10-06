package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.football.PrincipalReferee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class PrincipalRefereeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        PrincipalReferee principalReferee =
                new PrincipalReferee("Jose María", "León", 38);
        this.webTestClient
                .post()
                .uri(PrincipalRefereeResource.PRINCIPALREFEREES)
                .body(BodyInserters.fromValue(principalReferee))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PrincipalReferee.class)
                .value(Assertions::assertNotNull);
    }
}
