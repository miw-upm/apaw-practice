package es.upm.miw.apaw_practice.adapters.rest.night_life;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class OwnerNightLifeResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void TestCreate(){
        Owner owner = new Owner("Chano","123456789", "chano@example.es");
        this.webTestClient
                .post()
                .uri(OwnerNightLifeResource.OWNERS)
                .body(BodyInserters.fromValue(owner))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class)
                .value(Assertions::assertNotNull);
    }
}


