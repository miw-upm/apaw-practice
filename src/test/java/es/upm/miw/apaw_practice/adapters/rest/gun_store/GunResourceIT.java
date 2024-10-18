package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.GunStoreSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class GunResourceIT {
    public static final int UPDATED_GUN_ID = 2;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(GunResource.GUNS + GunResource.NAME, "AK-47")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Gun.class)
                .value(Assertions::assertNotNull)
                .value(gunData -> {
                    assertEquals("Kalashnikov", gunData.getManufacturer());
                    assertEquals(new BigDecimal("1299.99"), gunData.getPrice());
                })
        ;
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .put()
                .uri(GunResource.GUNS + GunResource.ID, UPDATED_GUN_ID)
                .body(BodyInserters.fromValue(GunStoreSeederService.getNewDummyGun(UPDATED_GUN_ID)))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Gun.class)
                .value(Assertions::assertNotNull);
    }
}
