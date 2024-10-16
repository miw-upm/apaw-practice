package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class SetupResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void deleteTest(){
        webTestClient.delete()
                .uri(SetupResource.SETUPS + SetupResource.ID, 0)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
