package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class BossResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(BossResource.GAMEWOW_BOSSES + BossResource.NAME_DESCRIPTION + BossResource.EFFORT, "ICC","25N")
                .exchange()
                .expectStatus().isOk();
    }
}
