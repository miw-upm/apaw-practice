package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.domain.models.football.Stadium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.football.StadiumResource.STADIUMS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StadiumResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate_ok() {
        Stadium stadium = Stadium.builder()
                .stadiumId(1L)
                .officialName("Metropolitano-" + System.nanoTime())
                .capacity(68000)
                .roof(true)
                .build();

        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Stadium.class)
                .value(created -> {
                    assertThat(created).isNotNull();
                    assertThat(created.getOfficialName()).isEqualTo(stadium.getOfficialName());
                    assertThat(created.getCapacity()).isEqualTo(stadium.getCapacity());
                    assertThat(created.getRoof()).isEqualTo(stadium.getRoof());
                });
    }

    @Test
    void testCreate_badRequest() {
        Stadium stadium = Stadium.builder()
                .stadiumId(2L)
                .officialName("")
                .capacity(0)
                .roof(null)
                .build();

        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreate_conflict() {
        Stadium stadium = Stadium.builder()
                .stadiumId(3L)
                .officialName("Duplicate Stadium-" + System.nanoTime())
                .capacity(40000)
                .roof(true)
                .build();

        // Primera creación OK
        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated();

        // Segunda creación con mismo nombre -> 409
        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isEqualTo(409);
    }
}
