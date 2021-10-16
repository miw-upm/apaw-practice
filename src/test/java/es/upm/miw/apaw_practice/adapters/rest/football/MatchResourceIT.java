package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.MatchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.MatchEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class MatchResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MatchRepository matchRepository;

    @Test
    void testDelete() {
        MatchEntity matchEntityTest = new MatchEntity(LocalDateTime.of(2027, 10, 20, 21, 0), "cloudy", 10, null, null);
        matchEntityTest.setId("idTest");
        this.matchRepository.save(matchEntityTest);

        assertTrue(this.matchRepository.existsById("idTest"));

        this.webTestClient
                .delete()
                .uri(MatchResource.MATCHES + "/10")
                .exchange()
                .expectStatus().isOk();
        assertFalse(this.matchRepository.existsById("idTest"));
    }

    @Test
    void testUpdateWeather() {
        MatchWeatherDto matchWeatherDto = new MatchWeatherDto(LocalDateTime.of(2021, 10, 20, 21, 0), "clear");

        this.webTestClient
                .patch()
                .uri(MatchResource.MATCHES)
                .body(BodyInserters.fromValue(matchWeatherDto))
                .exchange()
                .expectStatus().isOk();
    }
}