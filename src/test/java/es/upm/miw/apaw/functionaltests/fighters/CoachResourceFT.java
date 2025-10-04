package es.upm.miw.apaw.functionaltests.fighters;

import es.upm.miw.apaw.domain.models.fighters.Coach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.fighters.CoachResource.COACHES;
import static es.upm.miw.apaw.adapters.resources.fighters.CoachResource.NAME_ID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class CoachResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadByFullName_ok() {
        webTestClient.get()
                .uri(COACHES + NAME_ID, "John Smith")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Coach.class)
                .value(coach -> {
                    assertThat(coach).isNotNull();
                    assertThat(coach.getFullName()).isEqualTo("John Smith");
                    assertThat(coach.getAcademy()).isEqualTo("Nova Gym");
                    assertThat(coach.getExperienceYears()).isEqualTo(12);
                });
    }

    @Test
    void testReadByFullName_notFound() {
        webTestClient.get()
                .uri(COACHES + NAME_ID, "no existe")
                .exchange()
                .expectStatus().isNotFound();
    }
}
