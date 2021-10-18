package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class FootballPlayerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll() {
        this.webTestClient
                .get()
                .uri(FootballPlayerResource.PLAYERS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(FootballPlayer.class)
                .value(players -> assertTrue(players.size() > 0))
                .value(players -> assertEquals(10, players.get(0).getGoalsScored()))
                .value(players -> assertTrue(players.get(3).isDefense()));
    }

   /* @Test
    void testFindGoalsByStadiumName() {
        WebTestClient.BodySpec<Integer, ?> goalsScored = this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FootballPlayerResource.PLAYERS + FootballPlayerResource.SEARCH)
                                .queryParam("q", "Bernabeu")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class);

        assertEquals(47, goalsScored.returnResult().getResponseBody());

        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FootballPlayerResource.PLAYERS + FootballPlayerResource.SEARCH)
                                .queryParam("q", "Reino de Leon")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }*/
}
