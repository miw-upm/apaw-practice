package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

@RestTestConfig
class OwnerClinicResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        OwnerClinic ownerClinic = new OwnerClinic("Marck", "Street Greece", "563789206");
        this.webTestClient
                .post()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerClinic))
                .exchange()
                .expectStatus().isOk()
                .expectBody(OwnerClinic.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void TestCreateConflit() {
        OwnerClinic ownerClinic = new OwnerClinic("Mary", "Street New York", null);
        this.webTestClient
                .post()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerClinic))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateName() {
        List<OwnerClinic> ownerClinicList = Arrays.asList(
                new OwnerClinic("Marcos", "Street Castle", "983888666"),
                new OwnerClinic("Juan", "Street Japan", "555222111")
        );
        this.webTestClient
                .patch()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerClinicList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateNamesNotFound() {
        List<OwnerClinic> ownerClinicList = Arrays.asList(
                new OwnerClinic("Tim", "Street Miami", "841841841")
        );
        this.webTestClient
                .patch()
                .uri(OwnerResource.OWNERS)
                .body(BodyInserters.fromValue(ownerClinicList))
                .exchange()
                .expectStatus().isNotFound();
    }
}