package es.upm.miw.apaw_practice.adapters.rest.military;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RestTestConfig
public class SoldierResourceIT {
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
}
