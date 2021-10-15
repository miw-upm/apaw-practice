package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class OwnerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadByDni() {
        this.webTestClient
                .get()
                .uri(OwnerResource.OWNERS + OwnerResource.DNI,"99999999A")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class);

    }
}
