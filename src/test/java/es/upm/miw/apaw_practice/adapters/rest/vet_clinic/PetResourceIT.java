package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@RestTestConfig
public class PetResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void deleteTest() {
        this.webTestClient
                .delete()
                .uri(PetResource.PETS + PetResource.NICKANDOWNER, "Lua", "Carmen")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateTest() {
        List<Diagnosis> diagnosisList = List.of(
                new Diagnosis("diagnosis1", "medicine1", false),
                new Diagnosis("diagnosis2", "", false),
                new Diagnosis("diagnosis5", "medicine5", false)
        );
        this.webTestClient
                .put()
                .uri(PetResource.PETS + PetResource.CHIP + PetResource.DIAGNOSIS, 2222)
                .body(BodyInserters.fromValue(diagnosisList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void findNicksByVetNumberTest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(PetResource.PETS + PetResource.SEARCH)
                                .queryParam("q", "vetNumber:111")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(nicks -> nicks.get(0), equalTo("[\"Cooper\",\"Makalu\"]"));
    }

}
