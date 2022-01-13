package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.training.ParticipantResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RestTestConfig
public class ParticipantResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(PARTICIPANTS + EMAIL, "juan99@gmail.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Participant.class)
                .value(Assertions::assertNotNull)
                .value(participantData -> {
                    assertEquals("Juan", participantData.getName());
                    assertFalse(participantData.getGraduate());
                    assertEquals(643167221, participantData.getPhone());
                    assertEquals(2, participantData.getCourses().size());
                    assertEquals("62001", participantData.getCourses().get(0).getIdentity());

                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(PARTICIPANTS + EMAIL, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(PARTICIPANTS + EMAIL, "kk")
                .exchange()
                .expectStatus().isOk();
    }
}
