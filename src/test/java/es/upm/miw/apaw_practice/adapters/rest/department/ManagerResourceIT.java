package es.upm.miw.apaw_practice.adapters.rest.department;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.department.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class ManagerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(ManagerResource.MANAGERS + ManagerResource.EMAIL_ID, "b.ana@company1.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Manager.class)
                .value(Assertions::assertNotNull)
                .value(managerData -> assertEquals("456456456", managerData.getPhoneNumber()));
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(ManagerResource.MANAGERS + ManagerResource.EMAIL_ID, "nadie@company1.com")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(ManagerResource.MANAGERS + ManagerResource.EMAIL_ID, "b.ana@company1.com")
                .exchange()
                .expectStatus().isOk();
    }
}
