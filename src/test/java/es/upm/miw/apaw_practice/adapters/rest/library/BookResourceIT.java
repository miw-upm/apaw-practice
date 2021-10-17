package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class BookResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testFindAll(){
        this.webTestClient
                .get()
                .uri(BookResource.BOOKS)
                .exchange()
                .expectStatus().isOk();

    }
}
