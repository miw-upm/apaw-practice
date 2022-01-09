package es.upm.miw.apaw_practice.adapters.rest.training;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Participant;
import es.upm.miw.apaw_practice.domain.models.training.ParticipantEmailUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class ParticipantResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll() {
        this.webTestClient
                .get()
                .uri(ParticipantResource.PARTICIPANTS)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Participant.class)
                .value(participants -> assertEquals(5, participants.size()))
                .value(participants -> assertEquals("Sebastian", participants.get(3).getName()));
    }

    @Test
    void testUpdateEmailsNotFound() {
        List<ParticipantEmailUpdating> participantEmailUpdatingList = Arrays.asList(
                new ParticipantEmailUpdating("4823468XF","herman@gamil.com")
        );
        this.webTestClient
                .patch()
                .uri(ParticipantResource.PARTICIPANTS)
                .body(BodyInserters.fromValue(participantEmailUpdatingList))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateEmails() {
        List<ParticipantEmailUpdating> participantEmailUpdatingList = Arrays.asList(
                new ParticipantEmailUpdating("4823468X","hkfladf@gamil.com"),
                new ParticipantEmailUpdating("4956789A","kaff@gmail.com")
        );
        this.webTestClient
                .patch()
                .uri(ParticipantResource.PARTICIPANTS)
                .body(BodyInserters.fromValue(participantEmailUpdatingList))
                .exchange()
                .expectStatus().isOk();
        assertEquals("kaff@gmail.com",this.webTestClient
                .get()
                .uri(ParticipantResource.PARTICIPANTS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Participant.class)
                .returnResult()
                .getResponseBody()
                .get(1)
                .getEmail());
    }
}
