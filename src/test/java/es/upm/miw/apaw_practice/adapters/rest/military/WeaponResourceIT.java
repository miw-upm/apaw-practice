package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class WeaponResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(WeaponResource.WEAPONS + WeaponResource.SERIALCODE_ID, "SN55667788")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindDistinctSoldierRanksByManufacturer() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WeaponResource.WEAPONS + WeaponResource.SEARCH)
                        .queryParam("q", "manufacturer:Colt")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<String>>() {
                })
                .consumeWith(response -> {
                    assertNotNull(response.getResponseBody());
                    List<String> rankList = response.getResponseBody();
                    assertTrue(rankList.containsAll(Arrays.asList("Lieutenant", "Corporal", "Captain", "Commandant")));
                    assertFalse(rankList.contains("Private"));
                });
    }

    @Test
    void testFindDistinctSoldierRanksByManufacturerNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WeaponResource.WEAPONS + WeaponResource.SEARCH)
                        .queryParam("q", "manufacturer:Samsung")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<String>>() {
                })
                .value(ranks -> assertEquals(ranks.size(), 0));
    }

    @Test
    void testFindDistinctSoldierRanksByManufacturerBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(WeaponResource.WEAPONS + WeaponResource.SEARCH)
                        .queryParam("q", "manufacture:Beretta")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
