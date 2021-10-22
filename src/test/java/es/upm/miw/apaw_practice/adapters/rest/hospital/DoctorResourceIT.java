package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class DoctorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadDoctorNicks(){
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(DoctorResource.DOCTORS + DoctorResource.NICK)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Doctor.class)
                .value(doctors -> assertTrue(doctors.size() > 0))
                .value(doctors -> assertEquals("John", doctors.get(0).getNick()))
                .value(doctors -> assertEquals("Marta", doctors.get(1).getNick()))
                .value(doctors -> assertEquals("Jose", doctors.get(2).getNick()));
    }

    @Test
    void testFindSurnamesByDiseaseSeverity(){
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(DoctorResource.DOCTORS + DoctorResource.SEARCH)
                        .queryParam("q","diseaseSeverity:true")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Doctor.class)
                .value(doctors -> assertTrue(doctors.size() > 0))
                .value(doctors -> assertEquals("Lopez", doctors.get(0).getSurname()));
    }
}
