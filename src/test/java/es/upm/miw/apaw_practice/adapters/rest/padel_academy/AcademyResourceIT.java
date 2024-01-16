package es.upm.miw.apaw_practice.adapters.rest.padel_academy;

import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.PadelAcademySeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Academy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class AcademyResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PadelAcademySeederService padelAcademySeederService;

    @AfterEach
    void resetDatabase() {
        this.padelAcademySeederService.deleteAll();
        this.padelAcademySeederService.seedDatabase();
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(AcademyResource.ACADEMIES + AcademyResource.NAME, "Ocio y Deporte Canal")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Academy.class)
                .value(Assertions::assertNotNull)
                .value(academy -> {
                    assertEquals("Ocio y Deporte Canal", academy.getName());
                    assertEquals("Madrid", academy.getCity());
                    assertEquals("Avda. Filipinas, esq. Pablo Iglesias, 28003", academy.getAddress());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(AcademyResource.ACADEMIES + AcademyResource.NAME, "Padel Canal")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(AcademyResource.ACADEMIES + AcademyResource.NAME, "Ocio y Deporte Canal")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateAddress() {
        this.webTestClient
                .put()
                .uri(AcademyResource.ACADEMIES + AcademyResource.NAME + AcademyResource.ADDRESS, "Ocio y Deporte Canal")
                .body(BodyInserters.fromValue("Nueva calle. Nos mudamos"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Academy.class)
                .value(Assertions::assertNotNull)
                .value(academy -> {
                    assertEquals("Nueva calle. Nos mudamos", academy.getAddress());
                    assertEquals("Madrid", academy.getCity());
                    assertEquals("Ocio y Deporte Canal", academy.getName());
                });
    }

    @Test
    void testFindAcademyAddressByInstructorName() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(AcademyResource.ACADEMIES + AcademyResource.SEARCH)
                                .queryParam("q", "name:Ana")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(academies -> {
                    assertFalse(academies.isEmpty());
                    academies.forEach(academy -> {
                        assertTrue(academy.contains("Avda. Filipinas, esq. Pablo Iglesias, 28003"));
                    });
                });
    }
}
