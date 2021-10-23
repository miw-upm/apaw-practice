package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class CourtResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGet(){
        this.webTestClient.get()
                .uri(CourtResource.COURTS + "/2" + CourtResource.EQUIPMENTS + CourtResource.SUM)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(totalPrice -> assertEquals(new BigDecimal("17.5"), totalPrice));

        this.webTestClient.get()
                .uri(CourtResource.COURTS + "/4" + CourtResource.EQUIPMENTS + CourtResource.SUM)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(totalPrice -> assertEquals(BigDecimal.ZERO, totalPrice));

        this.webTestClient.get()
                .uri(CourtResource.COURTS + "/8" + CourtResource.EQUIPMENTS + CourtResource.SUM)
                .exchange()
                .expectStatus().isNotFound();
    }
}
