package es.upm.miw.apaw_practice.adapters.rest.car_workshop;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class TyreResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDeleteByManufacturer() {
        this.webTestClient
                .delete()
                .uri(TyreResource.TYRES + TyreResource.MANUFACTURER, "Hankook")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindModelByOwnerNameAndRevision() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(TyreResource.TYRES + TyreResource.SEARCH)
                                .queryParam("q", "ownerName:John Doe;revision:true")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class);

    }

}
