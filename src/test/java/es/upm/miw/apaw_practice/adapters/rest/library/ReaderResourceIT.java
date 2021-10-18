package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Gender;
import es.upm.miw.apaw_practice.domain.models.library.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class ReaderResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Reader reader = new Reader("Carlos", Gender.M, "ca@xpto.com");
        this.webTestClient
                .post()
                .uri(ReaderResource.READERS)
                .body(BodyInserters.fromValue(reader))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(ReaderResource.READERS + ReaderResource.EMAIL_ID, "jlo@xpto.com")
                .exchange()
                .expectStatus().isOk();
    }
}
