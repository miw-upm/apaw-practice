package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.library.AuthorResource;
import es.upm.miw.apaw_practice.domain.models.Class.Class;
import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class ClassResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testList() {
        this.webTestClient
                .get()
                .uri(ClassResource.theClass + ClassResource.theList)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Class.class)
                .value(list -> assertTrue(list.size() > 0));
    }
}
