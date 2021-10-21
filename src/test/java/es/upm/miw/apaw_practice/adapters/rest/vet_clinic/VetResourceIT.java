package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class VetResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createTest() {
        Vet vet = Vet.builder()
                .vetNumber(555)
                .name("vet5")
                .surname("surname5")
                .build();
        this.webTestClient
                .post()
                .uri(VetResource.VETS)
                .body(BodyInserters.fromValue(vet))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void createVetConflictTest() {
        Vet vet = Vet.builder()
                .vetNumber(111)
                .name("repeated")
                .surname("repeated")
                .build();
        this.webTestClient
                .post()
                .uri(VetResource.VETS)
                .body(BodyInserters.fromValue(vet))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
