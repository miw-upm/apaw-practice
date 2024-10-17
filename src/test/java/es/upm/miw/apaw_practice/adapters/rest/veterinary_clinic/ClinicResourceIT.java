package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Clinic;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class ClinicResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(ClinicResource.CLINICS + ClinicResource.NAME_ID, "Veterinary Clinic Happy Life")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clinic.class)
                .value(clinic -> {
                    assertEquals("Street San Francisco", clinic.getAddress());
                    assertTrue(clinic.getEmployees().stream()
                            .map(Employee::getName)
                            .toList()
                            .contains("Paco"));
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(ClinicResource.CLINICS + ClinicResource.NAME_ID, "Happy Heal")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByName() {
        this.webTestClient
                .delete()
                .uri(ClinicResource.CLINICS + ClinicResource.NAME_ID, "Happy Heal")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindByOwnerNameSumAge() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ClinicResource.CLINICS + ClinicResource.SEARCH + ClinicResource.TOTAL_SUM_OF_AGE)
                                .queryParam("q", "clinicName:Veterinary Clinic Happy Life;ownerName:Marcos")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(sum -> assertEquals(BigDecimal.valueOf(10), sum));
    }
}