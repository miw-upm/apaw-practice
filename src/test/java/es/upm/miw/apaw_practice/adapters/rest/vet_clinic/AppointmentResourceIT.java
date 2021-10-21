package es.upm.miw.apaw_practice.adapters.rest.vet_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

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

    @Test
    void findByConsumedTest() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path(AppointmentResource.APPOINTMENT + AppointmentResource.SEARCH)
                        .queryParam("q", "consumed:false")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Appointment.class)
                .value(List::size, equalTo(2))
                .value(appointmentDTOList -> appointmentDTOList.get(0).getConsumed(), equalTo(false))
                .value(appointmentDTOList -> appointmentDTOList.get(1).getConsumed(), equalTo(false));
    }
}
