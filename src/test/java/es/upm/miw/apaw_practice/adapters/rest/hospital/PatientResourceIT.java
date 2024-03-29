package es.upm.miw.apaw_practice.adapters.rest.hospital;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class PatientResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetByName() {
        this.webTestClient
                .get()
                .uri(PatientResource.PATIENTS + PatientResource.SOCIAL_INSURANCE_NUMBER, "000000000")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Patient.class)
                .value(Assertions::assertNotNull)
                .value(patient -> {
                    assertEquals("000000000", patient.getSocialInsuranceNumber());
                    assertEquals("NONE", patient.getAllergicMedicine());
                    assertEquals(2, patient.getDoctors().size());
                    assertEquals(Boolean.TRUE, patient.getAppointments().get(0).getUrgent());
                    assertEquals("101", patient.getAppointments().get(1).getAppointmentRoom());
                    assertEquals(LocalDate.of(1999,1,1), patient.getBirthDate());
                });
    }

    @Test
    void testPatchEndpoint() {
        this.webTestClient
                .patch()
                .uri(PatientResource.PATIENTS + PatientResource.SOCIAL_INSURANCE_NUMBER + PatientResource.ALLERGIC_MEDICINE, "333333333")
                .body(BodyInserters.fromValue("TestAllergicMedicine"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Patient.class)
                .value(Assertions::assertNotNull)
                .value(patient -> {
                    assertEquals("333333333", patient.getSocialInsuranceNumber());
                    assertEquals("TestAllergicMedicine",patient.getAllergicMedicine());
                });
    }

    @Test
    void testSearchOccupiedBedsByAppointmentRoomAndUrgent() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(PatientResource.PATIENTS + PatientResource.SEARCH_AVAILABLE)
                                .queryParam("appointmentRoom", "123")
                                .queryParam("urgent", "true")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(result -> Assertions.assertEquals("17", result));
    }

    @Test
    void testSearchAppointmentBySpeciality() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(PatientResource.PATIENTS + PatientResource.SEARCH_APPOINTMENT)
                                .queryParam("speciality", "E01")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(result -> Assertions.assertEquals("1", result));
    }


}
