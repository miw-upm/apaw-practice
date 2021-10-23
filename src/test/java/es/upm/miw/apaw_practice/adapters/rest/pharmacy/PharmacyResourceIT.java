package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.pharmacy.PharmacyDrugsUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

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

    @Test
    void testUpdateDrugs() {
        List<Drug> drugs = List.of(new Drug("1234","Drug 1",true,new BigDecimal(2.30)));
        this.webTestClient
                .patch()
                .uri(PharmacyResource.PHARMACIES)
                .body(BodyInserters.fromValue(List.of(new PharmacyDrugsUpdating("1234", drugs))))
                .exchange()
                .expectStatus().isNotFound();
    }
}
