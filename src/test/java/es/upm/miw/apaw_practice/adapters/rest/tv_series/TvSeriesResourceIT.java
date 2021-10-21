package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.tv_series.Episode;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeriesFinishedUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.apaw_practice.adapters.rest.tv_series.TvSeriesResource.*;

@RestTestConfig
public class TvSeriesResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(TV_SERIES +TITLE, "Fairy Tail")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateFinished() {
        TvSeriesFinishedUpdating tvSeriesFinishedUpdating = new TvSeriesFinishedUpdating("Fairy Tail",false);
        this.webTestClient
                .patch()
                .uri(TV_SERIES)
                .body(BodyInserters.fromValue(tvSeriesFinishedUpdating))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateFinishedNotFound() {
        TvSeriesFinishedUpdating tvSeriesFinishedUpdating = new TvSeriesFinishedUpdating("Anohana", true);
        this.webTestClient
                .patch()
                .uri(TV_SERIES)
                .body(BodyInserters.fromValue(tvSeriesFinishedUpdating))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateEpisode() {
        Episode episode = new Episode(1,2,25);
        this.webTestClient
                .post()
                .uri(TV_SERIES + TITLE + EPISODES, "Kimetsu No Yaiba")
                .body(BodyInserters.fromValue(episode))
                .exchange()
                .expectStatus().isOk();
    }
}
