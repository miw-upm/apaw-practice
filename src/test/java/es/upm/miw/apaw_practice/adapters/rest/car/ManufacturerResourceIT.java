package es.upm.miw.apaw_practice.adapters.rest.car;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.ManufacturerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@RestTestConfig
public class ManufacturerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ManufacturerPersistence manufacturerPersistence;

    @Test
    void testUpdateNonExisting() {
        Manufacturer manufacturer = new Manufacturer("BMW", "Italy", 2900);
        updateManufacturer(manufacturer).expectStatus().is4xxClientError();
    }

    @Test
    void testUpdateExisting() {
        Manufacturer manufacturer = manufacturerPersistence.readByName("Tesla");
        assertEquals("Tesla", manufacturer.getName());
        assertEquals("USA", manufacturer.getCountry());

        Manufacturer manufacturerUpdate = new Manufacturer("Tesla", "Germany", 6700);

        manufacturer.setName(manufacturerUpdate.getName());
        manufacturer.setCountry(manufacturerUpdate.getCountry());
        manufacturer.setNumberOfEmployees(manufacturerUpdate.getNumberOfEmployees());

        updateManufacturer(manufacturer).expectStatus().isOk();



    }

    private WebTestClient.ResponseSpec updateManufacturer(Manufacturer manufacturer) {
        return webTestClient
                .put()
                .uri(ManufacturerResource.MANUFACTURER + ManufacturerResource.NAME_ID, manufacturer.getName())
                .body(BodyInserters.fromValue(manufacturer))
                .exchange();
    }

    @Test
    void testFindOwnerNamesByManufacturerCountry() {
        String country = "France";
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(ManufacturerResource.MANUFACTURER + ManufacturerResource.SEARCH)
                        .queryParam("q", "country:"+country)
                        .build(country))
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .value(names -> {
                    assertEquals(1, names.size());
                    assertTrue("Lucia", names.contains("Lucia"));
                });
    }
}
