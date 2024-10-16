package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class GunResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead(){
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
}
