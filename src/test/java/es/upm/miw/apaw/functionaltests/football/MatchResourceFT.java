package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.adapters.resources.football.MatchResource;
import es.upm.miw.apaw.domain.models.football.Match;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class MatchResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll_ok() {
        this.webTestClient.get()
                .uri(MatchResource.MATCHES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Match.class)
                .value(matches -> assertThat(matches).hasSizeGreaterThanOrEqualTo(2));
    }

    @Test
    void testReadByMatchId_ok() {
        this.webTestClient.get()
                .uri(MatchResource.MATCHES + MatchResource.MATCH_ID, 1L)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Match.class)
                .value(match -> {
                    assertThat(match.getMatchId()).isEqualTo(1L);
                    assertThat(match.getHomeGoals()).isNotNull();
                });
    }

    @Test
    void testReadByMatchId_notFound() {
        this.webTestClient.get()
                .uri(MatchResource.MATCHES + MatchResource.MATCH_ID, 999L)
                .exchange()
                .expectStatus().isNotFound();
    }
}