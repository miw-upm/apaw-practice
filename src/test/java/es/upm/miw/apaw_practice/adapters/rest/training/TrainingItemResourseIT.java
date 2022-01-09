package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.TrainingItem;
import es.upm.miw.apaw_practice.domain.models.training.TrainingItemUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class TrainingItemResourseIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        TrainingItem trainingItem = this.webTestClient
                .get()
                .uri(TrainingItemResource.TRAININGITEMS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TrainingItem.class)
                .value(Assertions::assertNotNull)
                .returnResult()
                .getResponseBody()
                .get(0);
        TrainingItemUpdating trainingItemName = new TrainingItemUpdating("Scale analysis");
        this.webTestClient
                .put()
                .uri(TrainingItemResource.TRAININGITEMS + TrainingItemResource.ID_ID + TrainingItemResource.NAME,trainingItem.getId())
                .body(BodyInserters.fromValue(trainingItemName))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TrainingItem.class)
                .value(Assertions::assertNotNull);
    }
}
