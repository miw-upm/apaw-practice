package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.SoldierRankUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RestTestConfig
class SoldierResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindAll() {
        this.webTestClient
                .get()
                .uri(SoldierResource.SOLDIERS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Soldier.class)
                .value(soldiers -> soldiers.get(0).getIdentityDocument(), equalTo("12345678Z"))
                .value(soldiers -> assertFalse(soldiers.isEmpty()));
    }

    @Test
    void testUpdateRanks() {
        List<SoldierRankUpdating> soldierRankUpdatingList = Arrays.asList(
                new SoldierRankUpdating("12345678Z", "Corporal"),
                new SoldierRankUpdating("87654321F", "Corporal")
        );
        this.webTestClient
                .patch()
                .uri(SoldierResource.SOLDIERS)
                .body(BodyInserters.fromValue(soldierRankUpdatingList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateRanksNotFound() {
        List<SoldierRankUpdating> soldierRankUpdatingList = Arrays.asList(
                new SoldierRankUpdating("87654321F", "Captain"),
                new SoldierRankUpdating("99887766V", "Corporal")
        );
        this.webTestClient
                .patch()
                .uri(SoldierResource.SOLDIERS)
                .body(BodyInserters.fromValue(soldierRankUpdatingList))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCountDistinctWeaponsByFullName() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(SoldierResource.SOLDIERS + SoldierResource.SEARCH)
                        .queryParam("q", "fullName:Ana Ramírez López")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .value(count -> assertEquals(4, count));
    }

    @Test
    void testCountDistinctWeaponsByFullNameNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(SoldierResource.SOLDIERS + SoldierResource.SEARCH)
                        .queryParam("q", "fullName:Ana López")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .value(count -> assertEquals(0, count));
    }

    @Test
    void testCountDistinctWeaponsByFullNameBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(SoldierResource.SOLDIERS + SoldierResource.SEARCH)
                        .queryParam("q", "name:Ana Ramírez López")
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
