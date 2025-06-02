package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class StageResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(StageResource.STAGES + StageResource.NAME_ID, "TestStage1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteNotFound() {
        this.webTestClient
                .delete()
                .uri(StageResource.STAGES + StageResource.NAME_ID, "TestStageNotFound")
                .exchange()
                .expectStatus().isNotFound();
    }
}