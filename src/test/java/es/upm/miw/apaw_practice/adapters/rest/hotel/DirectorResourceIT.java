package es.upm.miw.apaw_practice.adapters.rest.hotel;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class DirectorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadEmails(){
        this.webTestClient
                .get()
                .uri(DirectorResource.DIRECTORS)
                .exchange()
                .expectStatus().isOk();

    }
}
