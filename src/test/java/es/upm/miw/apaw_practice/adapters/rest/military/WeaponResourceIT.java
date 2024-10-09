package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class WeaponResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(WeaponResource.WEAPONS + WeaponResource.SERIALCODE_ID, "SN11223344")
                .exchange()
                .expectStatus().isOk();
    }
}
