package es.upm.miw.apaw.functionaltests.airport;

import es.upm.miw.apaw.adapters.resources.airport.BoardingGateResource;
import es.upm.miw.apaw.domain.models.airport.BoardingGate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class BoardingGateResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        webTestClient.put().uri(BoardingGateResource.BOARDING_GATES + "/aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003/opened")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(true)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BoardingGate.class)
                .value(boardingGate -> assertThat(boardingGate.getOpened()).isTrue());
    }
}
