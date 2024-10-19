package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.basketball.BasketBallResource.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class BasketBallResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateNotFound() {
        BasketPlayer player = new BasketPlayer("12345678A", "Lebron", 7, 30);
        BasketMatch basketMatch = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", List.of(player));
        BasketBall basketBall =
                new BasketBall(1, "Nike", new BigDecimal("50.0"), basketMatch);
        this.webTestClient
                .put()
                .uri(BALLS + ID_ID, "0")
                .body(BodyInserters.fromValue(basketBall))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        BasketPlayer player = new BasketPlayer("12345678A", "Lebron", 7, 30);
        BasketMatch basketMatch = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", List.of(player));
        BasketBall basketBall =
                new BasketBall(1, "Nike", new BigDecimal("50.0"), basketMatch);
        this.webTestClient
                .put()
                .uri(BALLS + ID_ID, "1")
                .body(BodyInserters.fromValue(basketBall))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BasketBall.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testGetDistinctBrands(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(BALLS + SEARCH + DISTINCT_BRANDS)
                                .queryParam("q", "league:NBA;playerName:Lebron")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(value -> assertTrue(value.contains("Nike")));
    }
}
