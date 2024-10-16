package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.Competitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class CompetitorResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetCompetitorNotFound(){
        this.webTestClient
                .get()
                .uri(CompetitorResource.COMPETITOR + CompetitorResource.LICENCE_ID, "NO/LICENCE")
                .exchange()
                .expectStatus().isNotFound();
    }
    @Test
    void testGetCompetitor(){
        this.webTestClient
                .get()
                .uri(CompetitorResource.COMPETITOR + CompetitorResource.LICENCE_ID, "WU/A/00126")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Competitor.class)
                .value(Assertions::assertNotNull)
                .value(competitor -> assertEquals(1, competitor.getFederatedYears()));

    }
}
