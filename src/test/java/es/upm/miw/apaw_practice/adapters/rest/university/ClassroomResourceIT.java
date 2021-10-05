package es.upm.miw.apaw_practice.adapters.rest.university;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.university.ClassroomResource.CLASSROOM_ID;
import static es.upm.miw.apaw_practice.adapters.rest.university.ClassroomResource.CLASSROOMS;

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
}
