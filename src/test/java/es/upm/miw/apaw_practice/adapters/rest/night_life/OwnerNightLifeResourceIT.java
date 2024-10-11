package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class OwnerNightLifeResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void TestCreate(){
        Owner owner = new Owner("Chano","123456789", "chano@example.es");
        this.webTestClient
                .post()
                .uri(OwnerNightLifeResource.OWNERS)
                .body(BodyInserters.fromValue(owner))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testReadOwnerByName() {
        String ownerName = "Owner1";
        this.webTestClient
                .get()
                .uri(OwnerNightLifeResource.OWNERS + OwnerNightLifeResource.NAME_ID, ownerName)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class)
                .value(owner -> {
                    assertEquals(ownerName, owner.getName());
                    assertEquals("123456789", owner.getPhone());
                    assertEquals("owner1@example.com", owner.getEmail());
                });
    }

    @Test
    void testCalculateTotalPriceByOwner() {

        this.webTestClient
                .get()
                .uri(OwnerNightLifeResource.OWNERS + OwnerNightLifeResource.NAME_ID + OwnerNightLifeResource.TOTAL_PRICE, "Owner1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(totalPrice ->
                        assertEquals(0, totalPrice.compareTo(BigDecimal.valueOf(100.00)))
                );
    }
}


