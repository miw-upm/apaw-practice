package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

@RestTestConfig
public class LecturerResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        Lecturer lecturer = new Lecturer("1468048B", LocalDate.of(2009,10,13),13);
        this.webTestClient
                .put()
                .uri(LecturerResource.LECTURERS + LecturerResource.DNI, "1468048B")
                .body(BodyInserters.fromValue(lecturer))
                .exchange()
                .expectStatus().isOk();
    }
}
