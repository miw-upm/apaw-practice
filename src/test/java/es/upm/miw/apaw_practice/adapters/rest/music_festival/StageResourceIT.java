package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class StageResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Stage stage = new Stage("RestStage", "RestLoc", 3000, null);
        this.webTestClient
                .post()
                .uri(StageResource.STAGES)
                .body(BodyInserters.fromValue(stage))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Stage.class)
                .value(stageResult -> {
                    assertEquals("RestStage", stageResult.getName());
                    assertEquals("RestLoc", stageResult.getLocation());
                    assertEquals(3000, stageResult.getCapacity());
                    assertNotNull(stageResult.getOpenTime());
                });
    }

    @Test
    void testCreateConflict() {
        Stage stage = new Stage("MainStage", "Loc", 1, LocalDateTime.now());
        this.webTestClient
                .post()
                .uri(StageResource.STAGES)
                .body(BodyInserters.fromValue(stage))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(StageResource.STAGES + StageResource.NAME_ID, "TestStage1")
                .exchange()
                .expectStatus().isOk();
    }

}