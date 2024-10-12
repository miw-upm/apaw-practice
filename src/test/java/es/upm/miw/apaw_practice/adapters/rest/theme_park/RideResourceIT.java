package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.theme_park.Ride;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.reactive.server.WebTestClient;


import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class RideResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByThemeAndMaxCapacityLessThan() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(RideResource.RIDES + RideResource.SEARCH)
                                .queryParam("q", "theme:Halloween;maxCapacity:90")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Ride.class)
                .value(rides -> assertFalse(rides.isEmpty()))
                .value(rides -> assertEquals("Halloween", rides.get(0).getTheme()))
                .value(rides -> assertTrue(rides.get(0).getMaxCapacity() < 90));
    }

    @Test
    void testFindByThemeAndMaxCapacityLessThanBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(RideResource.RIDES + RideResource.SEARCH)
                                .queryParam("q", "kk:aaa;maxCapacity:90")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
