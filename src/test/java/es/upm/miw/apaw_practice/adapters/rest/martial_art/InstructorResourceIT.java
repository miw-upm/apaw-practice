package es.upm.miw.apaw_practice.adapters.rest.martial_art;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.hotel_retired.HotelResource.*;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.InstructorResource.INSTRUCTOR;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.InstructorResource.DNI;
import static es.upm.miw.apaw_practice.adapters.rest.martial_art.InstructorResource.PHONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class InstructorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Instructor instructor = new Instructor(
                "Z15546666Z",
                "Eddie yellow",
                87539464,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2));
        this.webTestClient
                .post()
                .uri(INSTRUCTOR)
                .body(BodyInserters.fromValue(instructor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(INSTRUCTOR + DNI, "Z1521143C")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(Assertions::assertNotNull)
                .value(instructor -> {
                    assertEquals("Z1521143C", instructor.getDni());
                    assertEquals("Bastian red", instructor.getFullName());
                    assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), instructor.getBirthDate());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(INSTRUCTOR + DNI, "Z0000000")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Instructor instructor = new Instructor(
                "Z1672243C",
                "Jackie Chan",
                9999999,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        this.webTestClient
                .post()
                .uri(INSTRUCTOR)
                .body(BodyInserters.fromValue(instructor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(Assertions::assertNotNull);

        this.webTestClient
                .delete()
                .uri(INSTRUCTOR + DNI, "Z1672243C")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdate() {
        Instructor instructor = new Instructor(
                "Z1672243C",
                "Jackie Chan",
                9999999,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        this.webTestClient
                .post()
                .uri(INSTRUCTOR)
                .body(BodyInserters.fromValue(instructor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(Assertions::assertNotNull);

        instructor.setFullName("Kung lao");

        this.webTestClient
                .put()
                .uri(INSTRUCTOR + DNI, "Z1672243C")
                .body(BodyInserters.fromValue(instructor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(updatedInstructor -> {
                    assertEquals("Z1672243C", updatedInstructor.getDni());
                    assertEquals("Kung lao", updatedInstructor.getFullName());
                });
    }
    @Test
    void testUpdatePhone() {
        Instructor instructor = new Instructor(
                "Z2345243H",
                "Jackie Lee",
                87878787,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );

        this.webTestClient
                .post()
                .uri(INSTRUCTOR)
                .body(BodyInserters.fromValue(instructor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(Assertions::assertNotNull);

        Integer updatedPhone = 123456789;
        this.webTestClient
                .patch()
                .uri(INSTRUCTOR + DNI + PHONE, "Z2345243H")
                .body(BodyInserters.fromValue(updatedPhone))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Instructor.class)
                .value(updatedInstructor -> {
                    assertEquals(updatedPhone, updatedInstructor.getPhoneNumber());
                });
    }

}
