package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.DiseaseUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
public class DiseaseResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateDescriptions(){
        DiseaseUpdate diseaseUpdate1 = new DiseaseUpdate("Common cold", "New Description");
        DiseaseUpdate diseaseUpdate2 = new DiseaseUpdate("Conjunctivitis", "New Description 2");
        List<DiseaseUpdate> diseaseUpdates = List.of(diseaseUpdate1,diseaseUpdate2);

        this.webTestClient
                .patch()
                .uri(DiseaseResource.DISEASES)
                .body(BodyInserters.fromValue(diseaseUpdates))
                .exchange()
                .expectStatus().isOk();
    }
}
