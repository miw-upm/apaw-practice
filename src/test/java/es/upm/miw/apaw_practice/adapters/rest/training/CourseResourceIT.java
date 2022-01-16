package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.models.training.CoursePriceUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testUpdatePrices() {
        List<CoursePriceUpdating> coursePriceUpdatingList = Arrays.asList(
                new CoursePriceUpdating("62001", BigDecimal.ONE),
                new CoursePriceUpdating("62003", BigDecimal.TEN)
        );
        this.webTestClient
                .patch()
                .uri(CourseResource.COURSES)
                .body(BodyInserters.fromValue(coursePriceUpdatingList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePricesNotFound() {
        List<CoursePriceUpdating> coursePriceUpdatingList = Arrays.asList(
                new CoursePriceUpdating("0", BigDecimal.ONE),
                new CoursePriceUpdating("1", BigDecimal.TEN)
        );
        this.webTestClient
                .patch()
                .uri(CourseResource.COURSES)
                .body(BodyInserters.fromValue(coursePriceUpdatingList))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetCoursePriceSumByLecturerStartDate() {
        WebTestClient.BodySpec<BigDecimal, ?> totalPrice = this.webTestClient
                .get()
                .uri(CourseResource.COURSES + CourseResource.STARTDATE + "?date=2005-12-06")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(BigDecimal.class);
        assertEquals(new BigDecimal("965.59").setScale(2, HALF_EVEN), totalPrice.returnResult().getResponseBody());
    }
}
