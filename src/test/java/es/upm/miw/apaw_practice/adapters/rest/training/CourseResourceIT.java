package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
public class CourseResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(CourseResource.COURSES + CourseResource.ID_ID, "daf")
                .exchange()
                .expectStatus().isOk();
    }
}
