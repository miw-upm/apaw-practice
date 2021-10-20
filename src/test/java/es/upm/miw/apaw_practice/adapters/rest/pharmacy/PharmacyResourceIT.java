package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class PharmacyResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreatePharmacy() {
        this.webTestClient
                .post()
                .uri(PharmacyResource.PHARMACIES)
                .body(BodyInserters.fromValue(new Pharmacy()))
                .exchange()
                .expectStatus().isOk();
    }
}
