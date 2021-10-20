package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.university.ClassroomResource.*;

@RestTestConfig
public class ClassroomResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(CLASSROOMS + CLASSROOM_ID, "ETSISI", 3101)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindCapacitySumByStudentDni() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CLASSROOMS + SEARCH)
                                .queryParam("q", "studentDni:112233445E")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .value(capacitySum -> Assertions.assertEquals(capacitySum, 70));
    }

    @Test
    void testFindCapacitySumByStudentDniNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CLASSROOMS + SEARCH)
                                .queryParam("q", "studentDni:111")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindCapacitySumByStudentDniBadRequest() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CLASSROOMS + SEARCH)
                                .queryParam("q", "112233445E")
                                .build())
                .exchange()
                .expectStatus().isBadRequest();
    }
}
