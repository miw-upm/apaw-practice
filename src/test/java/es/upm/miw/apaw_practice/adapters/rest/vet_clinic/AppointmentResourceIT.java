package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Diagnosis;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Pet;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Vet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestTestConfig
public class AppointmentResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void updateConsumedTest() {
        List<Appointment> appointmentsList = Arrays.asList();
        this.webTestClient
                .patch()
                .uri(AppointmentResource.APPOINTMENT)
                .body(BodyInserters.fromValue(appointmentsList))
                .exchange()
                .expectStatus().isOk();
    }
}
