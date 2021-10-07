package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class AnimalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        Animal animal = new Animal("Gato", "Felino", "Omnívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteNotFound() {
        Animal animal = new Animal("Gato", "Felino", "Pienso");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isNotFound();
    }
}
