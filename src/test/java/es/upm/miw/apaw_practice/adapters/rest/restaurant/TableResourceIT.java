package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RestTestConfig
class TableResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadHoldersByNumber(){
        this.webTestClient
                .get()
                .uri(TableResource.TABLES+"/1"+TableResource.RESERVES+TableResource.HOLDER)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Reserve.class)
                .value(reserves -> assertEquals("Jose",reserves.get(0).getHolder()))
                .value(reserves -> assertEquals("Paco",reserves.get(1).getHolder()));
    }
}
