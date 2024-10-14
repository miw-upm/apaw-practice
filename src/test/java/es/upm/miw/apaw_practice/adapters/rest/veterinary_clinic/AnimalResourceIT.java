package es.upm.miw.apaw_practice.adapters.rest.veterinary_clinic;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.LocalDateTime;

@RestTestConfig
class AnimalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

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