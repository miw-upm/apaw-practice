package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class StadiumResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Stadium stadium = new Stadium("Madrid", "Nuevo Bernabeu", "Real Madrid", null);

        this.webTestClient
                .put()
                .uri(StadiumResource.STADIUMS + "/Madrid" + StadiumResource.NAME)
                .body(BodyInserters.fromValue(stadium))
                .exchange()
                .expectStatus().isOk();

        stadium.setName("Bernabeu");
        this.webTestClient
                .put()
                .uri(StadiumResource.STADIUMS + "/Madrid" + StadiumResource.NAME)
                .body(BodyInserters.fromValue(stadium))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateCityNotFound() {
        Stadium stadium = new Stadium("Madrid", "Nuevo Bernabeu dos", "Real Madrid", null);

        this.webTestClient
                .put()
                .uri(StadiumResource.STADIUMS + "/Madridd" + StadiumResource.NAME)
                .body(BodyInserters.fromValue(stadium))
                .exchange()
                .expectStatus().isNotFound();
    }
}
