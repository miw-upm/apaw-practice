package es.upm.miw.apaw_practice.adapters.rest.zoo;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos.AnimalRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.AnimalEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.junit.jupiter.api.Assertions;
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

    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    void beforeEach() {
        AnimalEntity entity = new AnimalEntity(new Animal("Pato", "Ave", "Omnívoro"));
        entity.setId("id1");
        this.animalRepository.save(entity);
    }

    @Test
    void testDelete() {
        Animal animal = new Animal("Pato", "Ave", "Omnívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isOk();
        Assertions.assertFalse(this.animalRepository.existsById("id1"));
    }

    @Test
    void testDeleteDifferentDiet() {
        Animal animal = new Animal("Pato", "Ave", "Lombrices");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isOk();
        Assertions.assertFalse(this.animalRepository.existsById("id1"));
    }

    @Test
    void testDeleteNotFound() {
        Animal animal = new Animal("Pato", "Ave acuática", "Omnívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isNotFound();
        animal = new Animal("Pato Ánade", "Ave", "Omívoro");
        this.webTestClient
                .method(HttpMethod.DELETE)
                .uri(AnimalResource.ANIMALS)
                .body(BodyInserters.fromValue(animal))
                .exchange()
                .expectStatus().isNotFound();
        Assertions.assertTrue(this.animalRepository.existsById("id1"));
    }
}
