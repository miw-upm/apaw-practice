package es.upm.miw.apaw_practice.adapters.rest.tv_series;

/*
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import static es.upm.miw.apaw_practice.adapters.rest.tv_series.PlayerSeriesResource.PLAYER_SERIES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class PlayerSeriesResourceIT {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testReadAll() {
        this.webTestClient
                .get()
                .uri(PLAYER_SERIES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Player.class)
                .value(players -> assertEquals(4,players.size()))
                .value(players -> assertEquals("Brittney Karbowski",players.get(3).getName()));
    }
}

 */