package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class DropResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void findByEffort() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(DropResource.GAMEWOW_DROPS + DropResource.SEARCH)
                        .queryParam("q", "effort:10N")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Drop.class)
                .value(dropReferenceDtoList -> dropReferenceDtoList.get(0).getTitle(), equalTo("Plaguebringer's Stained Pants"))
                .value(dropReferenceDtoList -> assertTrue(dropReferenceDtoList.size() > 0));
    }
}
