package es.upm.miw.apaw_practice.adapters.rest.emarketer;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.EmarketerSeederService;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos.CupsRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CupsEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.emarketer.Cups;
import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class CupsResourceIT {

    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EmarketerSeederService emarketerSeederService;

    @Test
    void testUpdate() {
        assertTrue(this.cupsRepository.findByCups("AAPPZZZ6KZ1R149946").isPresent());
        CupsEntity currentCups = this.cupsRepository.findByCups("AAPPZZZ6KZ1R149946").get();
        assertEquals(new BigDecimal("912.31"), currentCups.getEnergy());
        assertEquals(LocalDateTime.of(2021, 10, 12, 19, 00, 00), currentCups.getRegistrationDate());
        assertEquals("Paula", currentCups.getCustomerEntity().getName());

        Cups cups = new Cups("AAPPZZZ6KZ1R149946", new BigDecimal("955.31"), LocalDateTime.of(2021, 10, 12, 19, 00, 00), new Customer("Paula", "Sevilla", "particular"));

        this.webTestClient
                .put()
                .uri(CupsResource.CUPS + CupsResource.CUP, cups.getCups())
                .body(BodyInserters.fromValue(cups))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Cups.class);

        assertTrue(this.cupsRepository.findByCups("AAPPZZZ6KZ1R149946").isPresent());
        CupsEntity updatedCups = this.cupsRepository.findByCups("AAPPZZZ6KZ1R149946").get();
        assertEquals(new BigDecimal("955.31"), updatedCups.getEnergy());
        assertEquals("Paula", updatedCups.getCustomerEntity().getName());
        emarketerSeederService.deleteAll();
        emarketerSeederService.seedDatabase();

    }


}
