package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class CaretakerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByDni() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CaretakerResource.CARETAKERS + CaretakerResource.SEARCH)
                                .queryParam("dni", "71679884Q")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Caretaker.class)
                .value(caretakers -> Assertions.assertEquals(1, caretakers.size()))
                .value(caretakers -> Assertions.assertEquals("71679884Q", caretakers.get(0).getDni()))
                .value(caretakers -> Assertions.assertEquals("Samuel L", caretakers.get(0).getName()))
                .value(caretakers -> Assertions.assertEquals("Jackson", caretakers.get(0).getSurname()));
    }

    @Test
    void testFindByDniNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CaretakerResource.CARETAKERS + CaretakerResource.SEARCH)
                                .queryParam("dni", "dniestamal")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}
