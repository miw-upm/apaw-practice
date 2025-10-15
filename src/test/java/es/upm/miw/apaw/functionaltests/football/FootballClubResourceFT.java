package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.adapters.resources.football.FootballClubResource;
import es.upm.miw.apaw.domain.models.football.FootballClub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

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
                    .expectBody(String.class)
                    .value(json -> {
                        System.out.println("🔹 JSON devuelto por el endpoint:");
                        System.out.println(json);
                    });
        }


    @Test
    void testReadByName_notFound() {
        this.webTestClient
                .get()
                .uri(FootballClubResource.FOOTBALL_CLUBS + FootballClubResource.NAME_ID, "NoExiste")
                .exchange()
                .expectStatus().isNotFound();
    }
}
