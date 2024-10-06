package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.competition.PlayerTeamResource.*;

@RestTestConfig
class PlayerTeamResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(PLAYER_TEAM + ID_ID, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}
