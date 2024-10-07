package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class OrganizationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateInternational() {
        this.webTestClient
                .patch()
                .uri(OrganizationResource.ORGANIZATION + OrganizationResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();

    }
}
