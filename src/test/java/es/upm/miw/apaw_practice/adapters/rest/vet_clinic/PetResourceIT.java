package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
public class PetResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void deleteTest() {
        this.webTestClient
                .delete()
                .uri(PetResource.PETS + PetResource.NICKANDOWNER, "Neko", "Carmen")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateTest() {
        List<Diagnosis> diagnosisList = List.of(
                new Diagnosis("diagnosis1", "medicine1", false),
                new Diagnosis("diagnosis2", "", false),
                new Diagnosis("diagnosis5", "medicine5", true)
        );
        this.webTestClient
                .put()
                .uri(PetResource.PETS + PetResource.CHIP + PetResource.DIAGNOSIS, 2222)
                .body(BodyInserters.fromValue(diagnosisList))
                .exchange()
                .expectStatus().isOk();
    }

}
