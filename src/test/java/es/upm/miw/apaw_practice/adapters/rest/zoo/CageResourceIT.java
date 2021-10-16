package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.ZooAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class CageResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindZooAddressesByCageLocationCode() {
        ZooAddress address = new ZooAddress("Portobello Road", 301, "GB 11K");
        this.webTestClient
                .get()
                .uri(CageResource.CAGES + "/2" + CageResource.ZOOS_ADDRESSES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ZooAddress.class)
                .value(values -> Assertions.assertEquals(1, values.size()))
                .value(values -> Assertions.assertEquals(address, values.get(0)));
    }

    @Test
    void testFindZooAddressesByCageLocationCodeMultiple() {
        ZooAddress address1 = new ZooAddress("Portobello Road", 301, "GB 11K");
        ZooAddress address2 = new ZooAddress("Mulholland Drive", 141, "HLS33");
        this.webTestClient
                .get()
                .uri(CageResource.CAGES + "/1" + CageResource.ZOOS_ADDRESSES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ZooAddress.class)
                .value(values -> Assertions.assertEquals(2, values.size()))
                .value(values -> Assertions.assertEquals(address1, values.get(0)))
                .value(values -> Assertions.assertEquals(address2, values.get(1)));
    }

    @Test
    void testFindZooAddressesByCageLocationCodeNone() {
        this.webTestClient
                .get()
                .uri(CageResource.CAGES + "/locationCodeNoCage" + CageResource.ZOOS_ADDRESSES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ZooAddress.class)
                .value(values -> Assertions.assertEquals(0, values.size()));
    }
}
