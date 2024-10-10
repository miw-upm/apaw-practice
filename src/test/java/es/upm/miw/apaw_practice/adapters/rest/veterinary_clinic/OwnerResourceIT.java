package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

@RestTestConfig
class OwnerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        Owner owner = new Owner("Marck", "Street Greece", "563789206");
        this.webTestClient
                .post()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(owner))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Owner.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void TestCreateConflit() {
        Owner owner = new Owner("Mary", "Street New York", null);
        this.webTestClient
                .post()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(owner))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateName() {
        List<Owner> ownerList = Arrays.asList(
                new Owner("Marcos", "Street Castle", "983888666"),
                new Owner("Juan", "Street Japan", "555222111")
        );
        this.webTestClient
                .patch()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateNamesNotFound() {
        List<Owner> ownerList = Arrays.asList(
                new Owner("Tim", "Street Miami", "841841841")
        );
        this.webTestClient
                .patch()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerList))
                .exchange()
                .expectStatus().isNotFound();
    }
}