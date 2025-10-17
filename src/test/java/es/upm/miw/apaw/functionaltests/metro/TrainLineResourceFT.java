package es.upm.miw.apaw.functionaltests.metro;
import es.upm.miw.apaw.adapters.resources.metro.TrainLineResource;
import es.upm.miw.apaw.domain.models.metro.TrainLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TrainLineResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        TrainLine trainLine = TrainLine.builder()
                .number(8)
                .color("Orange")
                .numStations(20)
                .circular(true)
                .trains(Collections.emptyList())
                .build();

        webTestClient.post()
                .uri(TrainLineResource.TRAIN_LINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trainLine)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TrainLine.class)
                .value(created -> assertThat(created).isNotNull());
    }

    @Test
    void testCreateNameConflict() {
        TrainLine trainLine = TrainLine.builder()
                .number(3) // Line 3 is already in the seeder.
                .color("Yellow")
                .numStations(20)
                .circular(true)
                .trains(Collections.emptyList())
                .build();

        webTestClient.post()
                .uri(TrainLineResource.TRAIN_LINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trainLine)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }


    @Test
    void testCreateBadRequestMissingField() {
        TrainLine trainLine = TrainLine.builder()
                .number(8) // several missing fields such as trains, numStations
                .color("Yellow")
                .build();

        webTestClient.post()
                .uri(TrainLineResource.TRAIN_LINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trainLine)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBadRequestNullNumber() {
        TrainLine trainLine = TrainLine.builder()
                .number(null) // It should fail because of null id.
                .color("Yellow")
                .numStations(20)
                .circular(true)
                .trains(Collections.emptyList())
                .build();

        webTestClient.post()
                .uri(TrainLineResource.TRAIN_LINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(trainLine)
                .exchange()
                .expectStatus().isBadRequest();
    }
}

