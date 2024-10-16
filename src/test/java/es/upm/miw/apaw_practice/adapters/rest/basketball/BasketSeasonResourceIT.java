package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.basketball.BasketSeasonResource.BASKETSEASONS;
import static java.util.Collections.emptyList;

@RestTestConfig
public class BasketSeasonResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BasketSeasonResource basketSeasonResource;

    @Test
    void testCreate(){
        BasketMatch match1 = new BasketMatch(1, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", emptyList());
        BasketMatch match2 = new BasketMatch(2, LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium B", emptyList());
        List<BasketMatch> matches = Arrays.asList(match1, match2);

        BasketSeason basketSeason = new BasketSeason(6, 2023, 2024, "Primera FEB", matches);

        this.webTestClient
                .post()
                .uri(BASKETSEASONS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(basketSeason))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BasketSeason.class)
                .value(Assertions::assertNotNull);
    }
}
