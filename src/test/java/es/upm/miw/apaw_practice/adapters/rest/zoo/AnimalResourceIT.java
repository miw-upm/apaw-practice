package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class AnimalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void createTemporalAnimal() {
        this.webTestClient
                .post()
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(new Animal("Narval", "Cetáceo", "Omnívoro")))
                .exchange();
    }

    @Test
    void testDelete() {
        Animal animal = new Animal("Narval", "Cetáceo", "Omnívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteNotFound() {
        Animal animal = new Animal("Narval", "Ballenato", "Omnívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isNotFound();
        animal.setFamily("Cetáceo");
        animal.setName("Ballena");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isNotFound();
    }
}
