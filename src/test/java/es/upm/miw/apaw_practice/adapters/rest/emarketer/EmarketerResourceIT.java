package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.EmarketerRepository;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.emarketer.Emarketer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testGetTotalPricePlanByCup() {
        WebTestClient.BodySpec<BigDecimal, ?> totalPrice = this.webTestClient
                .get()
                .uri(EmarketerResource.EMARKETER + EmarketerResource.CUPS + EmarketerResource.CUP, "AAPPZZZ6KZ1R149943")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(BigDecimal.class);

        assertEquals(new BigDecimal(70), totalPrice.returnResult().getResponseBody());
    }

}
