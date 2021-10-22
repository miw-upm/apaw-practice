package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.apaw_practice.adapters.rest.hospital.PatientResource.DNI_ID;
import static es.upm.miw.apaw_practice.adapters.rest.hospital.PatientResource.PATIENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RestTestConfig
class PatientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(PATIENTS + DNI_ID, "12357798M")
                .exchange()
                .expectStatus().isOk();

    }


    @Test
    void testUpdate() {
        Patient patient = new Patient("03468384F", "Male", 47, null, null);

        this.webTestClient
                .put()
                .uri(PatientResource.PATIENTS + DNI_ID, "03468384F")
                .body(BodyInserters.fromValue(patient))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Patient.class)
                .value(Assertions::assertNotNull)
                .value(updatedPatient -> assertEquals(47, updatedPatient.getAge()))
                .value(updatedPatient -> assertEquals("Male", updatedPatient.getGender()));
    }

}
