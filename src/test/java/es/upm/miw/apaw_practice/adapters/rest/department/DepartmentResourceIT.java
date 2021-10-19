package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class DepartmentResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindAll() {
        this.webTestClient
                .get()
                .uri(DepartmentResource.DEPARTMENTS)
                .exchange()
                .expectStatus().isOk();
    }
}
