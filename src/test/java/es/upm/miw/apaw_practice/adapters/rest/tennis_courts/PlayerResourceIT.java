package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class PlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void TestCreate(){
        Player player = new Player("0L", "Javier", "Fernandez", 43);
        this.webTestClient
                .post()
                .uri(PlayerResource.PLAYERS)
                .body(BodyInserters.fromValue(player))
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }
}
