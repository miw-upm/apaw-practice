package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.EmarketerRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class EmarketerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    EmarketerRepository emarketerRepository;

    @Autowired
    EmarketerSeederService emarketerSeederService;

    @AfterEach
    void seedDatabase() {
        this.emarketerSeederService.seedDatabase();
    }

    @Test
    void testDeleteByBookingNumber() {
        String name = "Comercializadora A";

        assertTrue(this.emarketerRepository.findAll().stream()
                .anyMatch(emarketerEntity ->
                        emarketerEntity.getName().equals(name)));

        this.webTestClient
                .delete()
                .uri(EmarketerResource.EMARKETER + EmarketerResource.NAME, name)
                .exchange()
                .expectStatus().isOk();

        assertFalse(this.emarketerRepository.findAll().stream()
                .anyMatch(emarketerEntity ->
                        emarketerEntity.getName().equals(name)));
    }


}
