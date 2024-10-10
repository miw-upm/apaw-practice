package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class OwnerCarResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(OwnerCarResource.OWNER + OwnerCarResource.DRIVERLICENSE, "YYSG34")
                .exchange();
        response.expectStatus().isOk();
        response.expectBody(OwnerCar.class);
        response.returnResult(OwnerCar.class).getResponseBody().subscribe(ownerCar -> {
            assertEquals("YYSG34", ownerCar.getDriverLicense());
            assertEquals("Marcos", ownerCar.getName());
            assertEquals(LocalDate.of(1984,8,30), ownerCar.getBirthDate());
        });
    }

    @Test
    void testReadNotFound() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(OwnerCarResource.OWNER + OwnerCarResource.DRIVERLICENSE, "HDDG66")
                .exchange();
        response.expectStatus().isNotFound();
    }

    @Test
    void testUpdateNameNotFound() {
        this.webTestClient
                .patch()
                .uri(OwnerCarResource.OWNER + OwnerCarResource.DRIVERLICENSE, "ZZZZ11")
                .body(BodyInserters.fromValue("Manuel"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .patch()
                .uri(OwnerCarResource.OWNER + OwnerCarResource.DRIVERLICENSE, "YYSG34")
                .body(BodyInserters.fromValue("Juan"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .value(Assertions::assertNotNull)
                .value(ownerCar -> assertEquals("Juan", ownerCar.getName()));
    }
}
