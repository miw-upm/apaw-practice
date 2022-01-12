package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

@RestTestConfig
public class CourseResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Course course =
                new Course("622004",1, new BigDecimal("298.98"));
        this.webTestClient
                .post()
                .uri(CourseResource.COURSES)
                .body(BodyInserters.fromValue(course))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Course course =
                new Course("62001",2, new BigDecimal("267.16"));
        this.webTestClient
                .post()
                .uri(CourseResource.COURSES)
                .body(BodyInserters.fromValue(course))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
