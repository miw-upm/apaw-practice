package es.upm.miw.apaw_practice.adapters.rest.wushu_sport;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class CompetitorResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CompetitorResource competitorResource;

    @Test
    void testGetCompetitor(){
        this.webTestClient
                .get()
                .uri(CompetitorResource.COMPETITOR + CompetitorResource.LICENCE_ID, "NO/LICENCE")
                .exchange()
                .expectStatus().isNotFound();
    }
}
