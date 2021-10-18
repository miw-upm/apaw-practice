package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class HospitalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        Hospital hospital = new Hospital("Test hospital", "Test St.", 1234, null);
        this.webTestClient
                .post()
                .uri(HospitalResource.HOSPITALS)
                .body(BodyInserters.fromValue(hospital))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Hospital.class)
                .value(Assertions::assertNotNull)
                .value(createdHospital -> assertEquals("Test hospital",createdHospital.getName()))
                .value(createdHospital -> assertEquals("Test St.", createdHospital.getAddress()))
                .value(createdHospital -> assertEquals(1234,createdHospital.getAvailableRooms()));
    }
}
