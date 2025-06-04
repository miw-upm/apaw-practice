package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestivalBudgetUpdating;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class MusicFestivalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateBudgets() {
        List<MusicFestivalBudgetUpdating> list = Arrays.asList(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(210000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(160000))
        );
        this.webTestClient
                .patch()
                .uri(MusicFestivalResource.FESTIVALS)
                .body(BodyInserters.fromValue(list))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateBudgetsNotFound() {
        List<MusicFestivalBudgetUpdating> list = List.of(
                new MusicFestivalBudgetUpdating("UnknownFest", BigDecimal.ONE)
        );
        this.webTestClient
                .patch()
                .uri(MusicFestivalResource.FESTIVALS)
                .body(BodyInserters.fromValue(list))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}