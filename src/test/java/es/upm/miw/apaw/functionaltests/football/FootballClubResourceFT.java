package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.adapters.resources.football.FootballClubBudgetDto;
import es.upm.miw.apaw.adapters.resources.football.FootballClubResource;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class FootballClubResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadByName_ok() {
        this.webTestClient
                .get()
                .uri(FootballClubResource.FOOTBALL_CLUBS + FootballClubResource.NAME_ID, "Salamanca FC")
                .exchange()
                .expectStatus().isOk()
                .expectBody(FootballClub.class)
                .value(club -> assertThat(club.getName()).isEqualTo("Salamanca FC"));
    }

    @Test
    void testReadByName_notFound() {
        this.webTestClient
                .get()
                .uri(FootballClubResource.FOOTBALL_CLUBS + FootballClubResource.NAME_ID, "NoExiste")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchBudget_ok() {
        this.webTestClient
                .patch()
                .uri(FootballClubResource.FOOTBALL_CLUBS + FootballClubResource.CLUB_ID, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new FootballClubBudgetDto(new BigDecimal("9999999")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(FootballClub.class)
                .value(club -> assertThat(club.getBudget()).isEqualByComparingTo(new BigDecimal("9999999")));
    }
}
