package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecsModification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class TyreSpecificationResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateLoadSpeedIndex(){
        TyreSpecsModification tyreSpecsModification = new TyreSpecsModification("90T", "95T");
        this.webTestClient
                .patch()
                .uri(TyreSpecificationResource.TYRE_SPECIFICATIONS + TyreSpecificationResource.LOAD_SPEED_INDEX)
                .body(BodyInserters.fromValue(tyreSpecsModification))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateLoadSpeedIndexBadRequest(){
        TyreSpecsModification tyreSpecsModification = new TyreSpecsModification();
        this.webTestClient
                .patch()
                .uri(TyreSpecificationResource.TYRE_SPECIFICATIONS + TyreSpecificationResource.LOAD_SPEED_INDEX)
                .body(BodyInserters.fromValue(tyreSpecsModification))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
