package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testUpdateActorNotExists(){
        Actor actor = new Actor("Jim Carrey", "James Eugene Carrey", false, LocalDate.of(1962,1,17));
        updateActor(actor).expectStatus().is4xxClientError();
    }

    @Test
    void testUpdateActor() {
        Actor actor = new Actor("Tom Hardy", "Edward Thomas Hardy", true, LocalDate.of(1977, 9, 15));
        actor.setRealName("Edward T. Hardy");
        actor.setIsAvailable(false);

        updateActor(actor).expectStatus().isOk();

        Actor updatedActor = getActorByArtisticName(actor.getArtisticName());
        assertEquals("Edward T. Hardy", updatedActor.getRealName());
        assertFalse(updatedActor.isAvailable());
    }

    private Actor getActorByArtisticName(String artisticName) {
        return webTestClient
                .get()
                .uri(ActorResource.ACTORS + "/" + artisticName)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Actor.class)
                .returnResult()
                .getResponseBody();
    }

    private WebTestClient.ResponseSpec updateActor(Actor actor) {
        return webTestClient
                .put()
                .uri(ActorResource.ACTORS)
                .body(BodyInserters.fromValue(actor))
                .exchange();
    }
}
