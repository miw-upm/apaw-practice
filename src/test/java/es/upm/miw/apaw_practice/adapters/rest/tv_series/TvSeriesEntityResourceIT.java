package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.tv_series.TvSeriesResource.TV_SERIES;
import static es.upm.miw.apaw_practice.adapters.rest.tv_series.TvSeriesResource.TITLE;
@RestTestConfig
public class TvSeriesEntityResourceIT {

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
}
