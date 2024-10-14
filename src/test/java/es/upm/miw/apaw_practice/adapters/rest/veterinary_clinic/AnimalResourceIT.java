package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class AnimalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        String name = "Lara";
        Animal updateAnimal = new Animal();
        updateAnimal.setAge(5);
        updateAnimal.setDateOfService(LocalDateTime.of(2019, 5, 10, 15, 10));
        updateAnimal.setOwnerClinic(new OwnerClinic("Peter", "Street Toledo", "980453215"));
        this.webTestClient
                .put()
                .uri(AnimalResource.ANIMALS + AnimalResource.ID_NAME, name)
                .body(BodyInserters.fromValue(updateAnimal))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Animal.class)
                .value(animal -> {
                    assertEquals(5, animal.getAge());
                    assertEquals(LocalDateTime.of(2019, 5, 10, 15, 10),
                            animal.getDateOfService());
                    assertEquals("Peter", animal.getOwnerClinic().getName());
                });
    }

    @Test
    void testUpdateNotFound() {
        String nameNotExist = "Apolo";
        Animal updateAnimal = new Animal();
        updateAnimal.setAge(7);
        updateAnimal.setOwnerClinic(new OwnerClinic("Aitana", "Street San Juan", "651234879"));
        updateAnimal.setDateOfService(LocalDateTime.of(2020, 10, 11, 5, 41));
        this.webTestClient
                .put()
                .uri(AnimalResource.ANIMALS + AnimalResource.ID_NAME, nameNotExist)
                .body(BodyInserters.fromValue(updateAnimal))
                .exchange()
                .expectStatus().isNotFound();
    }
}