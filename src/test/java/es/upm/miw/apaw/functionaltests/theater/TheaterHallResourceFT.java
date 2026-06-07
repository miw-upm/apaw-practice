package es.upm.miw.apaw.functionaltests.theater;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.resources.theater.TheaterHallResource;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TheaterHallResourceFT extends BaseTheaterTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THAL01")
                .hallName("Updated Theater Hall A")
                .hallCapacity(350)
                .hallAccessible(true)
                .build();

        webTestClient.put()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.HALL_CODE, "THAL01")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(hall)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TheaterHall.class)
                .value(updated -> {
                    assertThat(updated).isNotNull();
                    assertThat(updated.getHallCode()).isEqualTo("THAL01");
                    assertThat(updated.getHallName()).isEqualTo("Updated Theater Hall A");
                    assertThat(updated.getHallCapacity()).isEqualTo(350);
                    assertThat(updated.getHallAccessible()).isTrue();
                });
    }

    @Test
    void testUpdate_NotFound() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("NONEXISTENT")
                .hallName("No Hall")
                .hallCapacity(0)
                .hallAccessible(false)
                .build();

        webTestClient.put()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.HALL_CODE, "NONEXISTENT")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(hall)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate_BadRequest() {
        TheaterHall hall = TheaterHall.builder()
                .hallCode("THAL01")
                .hallName(null)
                .hallCapacity(-1)
                .hallAccessible(true)
                .build();

        webTestClient.put()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.HALL_CODE, "THAL01")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(hall)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testFindByMinCapacity() {
        webTestClient.get()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.SEARCH + "?minCapacity=150")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterHall.class)
                .value((List<TheaterHall> results) -> {
                    assertThat(results).hasSize(1);
                    assertThat(results.getFirst().getHallCode()).isEqualTo("THAL01");
                    assertThat(results.getFirst().getHallCapacity()).isEqualTo(300);
                });
    }

    @Test
    void testFindByMinCapacity_Boundary() {
        webTestClient.get()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.SEARCH + "?minCapacity=100")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterHall.class)
                .value((List<TheaterHall> results) -> assertThat(results).hasSize(2));
    }

    @Test
    void testFindByMinCapacity_NoResults() {
        webTestClient.get()
                .uri(TheaterHallResource.HALLS + TheaterHallResource.SEARCH + "?minCapacity=400")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterHall.class)
                .value((List<TheaterHall> results) -> assertThat(results).isEmpty());
    }
}
