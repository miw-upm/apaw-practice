package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

@RestTestConfig
class TeamCompetitionResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private TeamCompetitionResource teamCompetitionResource;

    @Test
    void testUpdatePlayersTeam() {
        PlayerTeam playerTeam = new PlayerTeam();
        playerTeam.setHeight(182.74);
        playerTeam.setWeight(80.51);
        playerTeam.setSalary(new BigDecimal("12.28"));

        List<PlayerTeam> playerTeams = List.of(playerTeam);

        this.webTestClient
                .put()
                .uri(TeamCompetitionResource.TEAM_COMPETITION + TeamCompetitionResource.ID_ID + TeamCompetitionResource.ARTICLE_ITEMS, "kk")
                .body(BodyInserters.fromValue(playerTeams))
                .exchange()
                .expectStatus().isNotFound();
    }
}
