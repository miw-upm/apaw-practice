package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.math.BigDecimal;

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
    void testCalculateTotalPriceByOwner() {

        this.webTestClient
                .get()
                .uri(OwnerNightLifeResource.OWNERS + OwnerNightLifeResource.NAME_ID + OwnerNightLifeResource.TOTAL_PRICE, "Owner1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(totalPrice ->
                        Assertions.assertTrue(totalPrice.compareTo(BigDecimal.valueOf(150.00)) == 0 ||
                                totalPrice.compareTo(BigDecimal.valueOf(199.98)) == 0)
                );
    }
}


