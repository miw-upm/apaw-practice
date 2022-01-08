package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RestTestConfig
public class LecturerResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Lecturer lecturer = new Lecturer("Maria", "8837149Y", "688129888");
        this.webTestClient
                .post()
                .uri(LecturerResource.LECTURERS)
                .body(BodyInserters.fromValue(lecturer))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Lecturer.class)
                .value(Assertions::assertNotNull)
                .value(lecturerId -> assertNotNull(lecturerId.getId()));
    }
}
