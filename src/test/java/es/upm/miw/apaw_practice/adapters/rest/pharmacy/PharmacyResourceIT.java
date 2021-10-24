package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import es.upm.miw.apaw_practice.domain.models.pharmacy.PharmacyDrugsUpdating;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class PharmacyResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreatePharmacy() {
        this.webTestClient
                .post()
                .uri(PharmacyResource.PHARMACIES)
                .body(BodyInserters.fromValue(new Pharmacy("789121", "Calle Rafael Ramos Cea nº28", 29002, List.of(new Drug("A9001", "Frenadol Complex", true, new BigDecimal("5.39"))))))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateDrugs() {
        List<Drug> drugs = List.of(new Drug("A9005","Drug 1",true,new BigDecimal("2.3")));
        this.webTestClient
                .patch()
                .uri(PharmacyResource.PHARMACIES)
                .body(BodyInserters.fromValue(List.of(new PharmacyDrugsUpdating("123456", drugs))))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindByDispensingNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(PharmacyResource.PHARMACIES + PharmacyResource.SEARCH)
                                .queryParam("q", "dispensing:15")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindByDispensing() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(PharmacyResource.PHARMACIES + PharmacyResource.SEARCH)
                                .queryParam("q", "dispensing:1")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Pharmacy.class)
                .value(pharmacy -> assertTrue(pharmacy.size() > 0))
                .value(pharmacy -> assertEquals("123456",pharmacy.get(0).getRegistrationNumber()));
    }
}
