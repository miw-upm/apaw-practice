package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class CompetitionResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetCompetition() {
        this.webTestClient
                .get()
                .uri(CompetitionResource.COMPETITION + CompetitionResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetNameCompetitionByPlayerId() {
        this.webTestClient
                .get()
                .uri(CompetitionResource.COMPETITION + CompetitionResource.TEAM_COMPETITION + CompetitionResource.PLAYER_TEAM + CompetitionResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);
    }
}
