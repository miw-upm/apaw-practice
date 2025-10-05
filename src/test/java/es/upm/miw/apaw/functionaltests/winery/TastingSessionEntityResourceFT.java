package es.upm.miw.apaw.functionaltests.winery;

import es.upm.miw.apaw.domain.models.winery.TastingSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.winery.TastingSessionResource.ID;
import static es.upm.miw.apaw.adapters.resources.winery.TastingSessionResource.TASTING_SESSIONS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class TastingSessionEntityResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        webTestClient.get()
                .uri(TASTING_SESSIONS + ID, UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TastingSession.class)
                .value(tastingSession -> {
                    assertThat(tastingSession).isNotNull();
                    assertThat(tastingSession.getCapacity()).isEqualTo(20);
                    assertThat(tastingSession.getDate().toString()).isEqualTo(LocalDate.now().plusDays(5).toString());
                    assertThat(tastingSession.getLocation()).isEqualTo("Main Hall");
                    assertThat(tastingSession.getWines()).hasSize(2);
                    assertThat(tastingSession.getWines().getFirst().getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
                    assertThat(tastingSession.getEvaluations()).hasSize(1);
                });
    }
}
