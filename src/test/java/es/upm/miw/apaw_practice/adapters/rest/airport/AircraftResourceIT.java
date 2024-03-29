package es.upm.miw.apaw_practice.adapters.rest.airport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;



@RestTestConfig
public class AircraftResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(AircraftResource.AIRCRAFTS + AircraftResource.NUMBER_PLATE, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}
