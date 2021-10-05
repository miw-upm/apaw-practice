package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Zoo;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class ZooResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        ZooAddress zooAddress = new ZooAddress("Via Azul", 55, "28001");
        Zoo zoo = new Zoo(zooAddress, 914356321);
        this.webTestClient
                .post()
                .uri(ZooResource.ZOOS)
                .body(BodyInserters.fromValue(zoo))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testCreateBadRequest() {
        Zoo zoo = new Zoo(null, 914356321);
        Assertions.assertNull(zoo.getAddress());
        Assertions.assertTrue(zoo.isNull());
        this.webTestClient
                .post()
                .uri(ZooResource.ZOOS)
                .body(BodyInserters.fromValue(zoo))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
