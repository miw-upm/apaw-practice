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
class ZooResourceIT {

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

    @Test
    void testUpdateZipCode() {
        ZooAddress zooAddressOld = new ZooAddress("Calle Carranza", 22, "28004");
        ZooAddress zooAddressParam = new ZooAddress("Via Azul", 55, "28001");
        ZooAddress zooAddressNew = new ZooAddress("Calle Carranza", 22, "28001");
        String id = "/id1";
        this.webTestClient
                .get()
                .uri(ZooResource.ZOOS + id)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Zoo.class)
                .value(zoos -> Assertions.assertEquals(1, zoos.size()))
                .value(zoos -> Assertions.assertEquals(zooAddressOld, zoos.get(0).getAddress()));
        this.webTestClient
                .put()
                .uri(ZooResource.ZOOS + id + ZooResource.ZIPCODE)
                .body(BodyInserters.fromValue(zooAddressParam))
                .exchange()
                .expectStatus().isOk();
        this.webTestClient
                .get()
                .uri(ZooResource.ZOOS + id)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Zoo.class)
                .value(zoos -> Assertions.assertEquals(1, zoos.size()))
                .value(zoos -> Assertions.assertEquals(zooAddressNew, zoos.get(0).getAddress()));
    }

    @Test
    void testUpdateZipCodeNotFound() {
        ZooAddress zooAddressParam = new ZooAddress("Via Azul", 55, "28001");
        this.webTestClient
                .put()
                .uri(ZooResource.ZOOS + "/idnotfound" + ZooResource.ZIPCODE)
                .body(BodyInserters.fromValue(zooAddressParam))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateZipCodeBadRequest() {
        ZooAddress zooAddressParam = new ZooAddress("Via Azul", 55, null);
        this.webTestClient
                .put()
                .uri(ZooResource.ZOOS + "/id1" + ZooResource.ZIPCODE)
                .body(BodyInserters.fromValue(zooAddressParam))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
