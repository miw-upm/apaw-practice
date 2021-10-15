package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class DirectorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadEmails() {
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Director.class)
                .value(directors -> Assertions.assertNull(directors.get(0).getDni()))
                .value(directors -> Assertions.assertNull(directors.get(1).getTelephone()))
                .value(directors -> Assertions.assertEquals("email@email.com", directors.get(1).getEmail()));
    }
}
