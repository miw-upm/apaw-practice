package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.adapters.mongodb.football.persistence.StadiumPersistenceMongodb;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class StadiumResourceIT {

    private final StadiumPersistenceMongodb stadiumPersistenceMongodb;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    public StadiumResourceIT(StadiumPersistenceMongodb stadiumPersistenceMongodb) {
        this.stadiumPersistenceMongodb = stadiumPersistenceMongodb;
    }

    @Test
    void testUpdate() {
        Stadium stadium = new Stadium("Madrid", "Nuevo Bernabeu", "Real Madrid", null);

        this.webTestClient
                .put()
                .uri(StadiumResource.STADIUMS + StadiumResource.CITY_ID, "kk")
                .body(BodyInserters.fromValue(stadium))
                .exchange()
                .expectStatus().isNotFound();

    }
}
