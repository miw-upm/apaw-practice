package es.upm.miw.apaw.functionaltests.metro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.metro.TrainStationResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TrainStationResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadCapacityByName() {
        webTestClient.get()
                .uri(TRAIN_STATIONS+NAME+CAPACITY, "Central Station")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(res -> assertEquals("500", res));
    }
}