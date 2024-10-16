package es.upm.miw.apaw_practice.adapters.rest.company;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.company.Management;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.UUID;

@RestTestConfig
public class ManagementResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Management management = new Management(UUID.randomUUID().toString(),"John Doe", true);
        this.webTestClient
                .post()
                .uri(ManagementResource.MANAGEMENTS)
                .body(BodyInserters.fromValue(management))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Management.class)
                .value(Assertions::assertNotNull);
    }

}

