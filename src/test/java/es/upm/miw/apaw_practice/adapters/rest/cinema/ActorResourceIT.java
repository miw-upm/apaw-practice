package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
class ActorResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Actor actor =
                new Actor("Elisabeth", "Banks", 47);
        this.webTestClient
                .post()
                .uri(ActorResource.ACTOR)
                .body(BodyInserters.fromValue(actor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Actor.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testUpdateActorAge() {
        List<Actor> actorList = List.of(new Actor[] {
                new Actor("Jennifer", "Lawrence", 32)
        });
        this.webTestClient
                .patch()
                .uri(ActorResource.ACTOR)
                .body(BodyInserters.fromValue(actorList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetSpectatorsNamesByAge() {
        this.webTestClient
                .get()
                .uri(ActorResource.ACTOR + ActorResource.AGE_ID + ActorResource.SPECTATORS_NAME, 30)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(name -> name.get(0).equals("Zoe"));
    }
}
