package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.models.gun_store.CompatibleAmmo;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class SetupResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void deleteTest() {
        webTestClient.delete()
                .uri(SetupResource.SETUPS + SetupResource.ID, 0)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void createTest() {
        webTestClient.post()
                .uri(SetupResource.SETUPS)
                .body(BodyInserters.fromValue(GunStoreSeederService.getNewDummySetup()))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Setup.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    public void testFindMostExpensiveByCaliberAndCategory() {
        webTestClient.get()
                .uri(uribuilder -> uribuilder.path(SetupResource.SETUPS + SetupResource.SEARCH)
                        .queryParam("q", "caliber:7.62x39mm;category:Scope")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Setup.class)
                .value(setup -> assertEquals(4, setup.getSetupId()))
                .value(setup -> assertEquals(new BigDecimal("4299.98"), setup.getTotalPrice()));
    }

}
