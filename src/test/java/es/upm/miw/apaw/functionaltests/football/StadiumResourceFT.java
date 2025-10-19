package es.upm.miw.apaw.functionaltests.football;

import es.upm.miw.apaw.domain.models.football.Stadium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

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
                .stadiumId(UUID.randomUUID())
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
                .stadiumId(UUID.randomUUID())
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
                .stadiumId(UUID.randomUUID())
                .officialName("Duplicate Stadium-" + System.nanoTime())
                .capacity(40000)
                .roof(true)
                .build();

        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated();

        this.webTestClient
                .post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isEqualTo(409);
    }

    @Test
    void testUpdateCapacity_ok() {
        Stadium stadium = Stadium.builder()
                .stadiumId(UUID.randomUUID())
                .officialName("Camp Nou-" + System.nanoTime())
                .capacity(99000)
                .roof(true)
                .build();

        this.webTestClient.post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated();

        Stadium updateRequest = Stadium.builder()
                .capacity(100000)
                .build();

        this.webTestClient.patch()
                .uri(STADIUMS + "/" + stadium.getOfficialName())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.capacity").isEqualTo(100000);
    }

   @Test
    void testDelete_ok() {
        Stadium stadium = Stadium.builder()
                .officialName("ToDelete-" + System.nanoTime())
                .capacity(45000)
                .roof(true)
                .build();

        // Crear primero
        this.webTestClient.post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated();

        // Eliminar
        this.webTestClient.delete()
                .uri(STADIUMS + "/" + stadium.getOfficialName())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDelete_notFound() {
        this.webTestClient.delete()
                .uri(STADIUMS + "/NoExists-" + System.nanoTime())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate_ok() {
        Stadium stadium = Stadium.builder()
                .officialName("ToUpdate-" + System.nanoTime())
                .capacity(40000)
                .roof(true)
                .build();

        this.webTestClient.post()
                .uri(STADIUMS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(stadium)
                .exchange()
                .expectStatus().isCreated();

        Stadium updated = Stadium.builder()
                .officialName("Updated-" + System.nanoTime())
                .capacity(50000)
                .roof(false)
                .build();

        this.webTestClient.put()
                .uri(STADIUMS + "/" + stadium.getOfficialName())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Stadium.class)
                .value(st -> {
                    assertThat(st.getOfficialName()).contains("Updated");
                    assertThat(st.getCapacity()).isEqualTo(50000);
                    assertThat(st.getRoof()).isFalse();
                });
    }

    @Test
    void testGetPlayersGoalsSum_ok() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(STADIUMS + "/Salamanca Stadium/players-goals-sum")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.sum").isEqualTo(21);
    }


}
