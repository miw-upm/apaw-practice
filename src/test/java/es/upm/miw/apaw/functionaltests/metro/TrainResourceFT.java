package es.upm.miw.apaw.functionaltests.metro;
import es.upm.miw.apaw.adapters.mongodb.metro.daos.TrainRepository;
import es.upm.miw.apaw.adapters.resources.metro.TrainResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TrainResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TrainRepository trainRepository;

    @Test
    void testDelete() {
        UUID trainId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200");

        assertThat(trainRepository.findById(trainId)).isPresent();

        webTestClient.delete()
                .uri(TrainResource.TRAINS + TrainResource.ID, trainId.toString())
                .exchange()
                .expectStatus().isOk();

        assertThat(trainRepository.findById(trainId)).isEmpty();
    }

    @Test
    void testDeleteNotFound() {
        UUID nonExistentTrainId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        assertThat(trainRepository.findById(nonExistentTrainId)).isEmpty();

        webTestClient.delete()
                .uri(TrainResource.TRAINS + TrainResource.ID, nonExistentTrainId.toString())
                .exchange()
                .expectStatus().isOk();

        assertThat(trainRepository.findById(nonExistentTrainId)).isEmpty();
    }
}