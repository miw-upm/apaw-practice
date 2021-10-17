package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.hospital.PatientResource.DNI_ID;
import static es.upm.miw.apaw_practice.adapters.rest.hospital.PatientResource.PATIENTS;


@RestTestConfig
public class PatientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(PATIENTS + DNI_ID, "03457384C")
                .exchange()
                .expectStatus().isOk();
    }
}
