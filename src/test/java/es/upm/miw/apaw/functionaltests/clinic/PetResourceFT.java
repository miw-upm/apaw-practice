package es.upm.miw.apaw.functionaltests.clinic;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.ClinicSeeder;
import es.upm.miw.apaw.adapters.resources.clinic.PetResource;
import es.upm.miw.apaw.domain.models.clinic.Gender;
import es.upm.miw.apaw.domain.models.clinic.Pet;
import es.upm.miw.apaw.domain.models.clinic.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class PetResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ClinicSeeder clinicSeeder;

    @BeforeEach
    void resetDb() {
        clinicSeeder.deleteAll();
        clinicSeeder.seedDatabase();
    }

    @Test
    void testUpdate() {
        Pet petRequest = Pet.builder().name("Max")
                .microchipNumber(ClinicSeeder.MICROCHIP_CHISPA).species(Species.DOG).gender(Gender.MALE).build();

        webTestClient.put()
                .uri(PetResource.PETS + PetResource.MICROCHIP, ClinicSeeder.MICROCHIP_CHISPA)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(petRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pet.class)
                .value(pet -> {
                    assertThat(pet.getName()).isEqualTo("Max");
                    assertThat(pet.getSpecies()).isEqualTo(Species.DOG);
                    assertThat(pet.getGender()).isEqualTo(Gender.MALE);
                });
    }
}